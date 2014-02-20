package se.liu.ida.erihe763.tddd78.tetris;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.Point;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class Board {

    private SquareType[][] squares;
    private List<BoardListener> listenerList;
    private Poly falling = null;
    private Point fallingPolyPos = null;
    private final static int OUTSIDE_FRAME = 1;
    private final static int TICK_DELAY = 200;
    private boolean gameOver = false;


    public Board(int width, int height) {
        squares = new SquareType[width][height];
        listenerList = new ArrayList<BoardListener>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width-1 || j == 0 || j == height-1) {
                    squares[i][j] = SquareType.OUTSIDE;
                }
                else {
                    squares[i][j] = SquareType.EMPTY;
                }
            }
        }
        this.notifyListeners();
    }

    public int getWidth() {
        return this.squares.length;
    }

    public int getHeight() {
        return this.squares[0].length;
    }

    public SquareType getSquareType(int x, int y) {
        SquareType fallingType = this.getFallingTypeAt(x, y);
        if (fallingType != SquareType.EMPTY) {
            return fallingType;
        }
        else {
            return this.getStationaryTypeAt(x, y);
        }
    }

    public SquareType getStationaryTypeAt(int x, int y) {
        return this.squares[x][y];
    }

    public SquareType getFallingTypeAt(int x, int y) {
        if (this.falling == null) {
            return SquareType.EMPTY;
        }
        for (int i = 0; i < this.falling.getSize(); i++) {
            for (int j = 0; j< this.falling.getSize(); j++) {
                if (x == (this.fallingPolyPos.x + i) && y == (this.fallingPolyPos.y + j)) {
                    return this.falling.getPolyTypeAt(i, j);
                }
            }
        }
        return SquareType.EMPTY;
    }


    public void setSquareType(int x, int y, SquareType st) {
        this.squares[x][y] = st;
        this.notifyListeners();
    }


    private final Action doOneStep = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    };

    private void tick() {
        if (this.gameOver) {
            return;
        }

        this.removeCompleteRows();

        if (this.falling != null) {
            if (this.validatePolyPosition(0, 1)) {
                this.descendPoly();
            }
            else {
                this.makeFallingStationary();
                this.resetFalling();
            }

        }
        else {
            this.falling = this.spawnPoly();
            this.fallingPolyPos = this.getSpawnPos();
            if (!this.validatePolyPosition(0, 0)) {
                this.endGame();
                return;
            }
        }

        this.notifyListeners();
    }

    private final Timer clockTimer = new Timer(TICK_DELAY, doOneStep);


    public void addBoardListener(BoardListener bl) {
        this.listenerList.add(bl);
    }

    private void notifyListeners() {
        for (BoardListener boardListener : listenerList) {
            boardListener.boardChanged();
        }
    }
    public void randomizeBoard() {
        SquareType[] types = SquareType.values();
        
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getHeight() - 1; j++) {
                this.setSquareType(i, j, types[new Random().nextInt(types.length - 1)]);
            }
        }

        this.notifyListeners();
    }

    public void clearBoard() {
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getHeight() - 1; j++) {
                this.setSquareType(i, j, SquareType.EMPTY);
            }
        }
    }

    public Poly spawnPoly() {
        TetrominoMaker tetrominoMaker = new TetrominoMaker();
        return tetrominoMaker.getPoly(new Random().nextInt(tetrominoMaker.getNumberOfTypes()));
    }

    public void descendPoly() {
        this.fallingPolyPos.y += 1;
    }

    public void moveLeft() {
        if (this.existFalling() && validatePolyPosition(-1, 0)) {
            this.fallingPolyPos.x -= 1;
            this.notifyListeners();
        }
    }

    public void moveRight() {
        if (this.existFalling() && validatePolyPosition(1, 0)) {
            this.fallingPolyPos.x += 1;
            this.notifyListeners();
        }
    }

    public void moveDown() {
        while (this.existFalling() && validatePolyPosition(0, 1)) {
            this.fallingPolyPos.y += 1;
        }
        this.notifyListeners();
    }


    private boolean validatePolyPosition(int xoffset, int yoffset) {
        // Check if any block in the falling polyomino that is not empty
        // may be moved one step down.
        for (int i = 0; i < this.falling.getSize(); i++) {
            for (int j = 0; j < this.falling.getSize(); j++) {
                if (this.falling.getPolyTypeAt(i, j) != SquareType.EMPTY &&
                        !this.isSquareEmpty(this.fallingPolyPos.x + i + xoffset,
                                this.fallingPolyPos.y + j + yoffset)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSquareEmpty(int x, int y) {
        if (this.getStationaryTypeAt(x, y) == SquareType.EMPTY) {
            return true;
        }
        return false;
    }

    private void removeCompleteRows() {
        for (int row = OUTSIDE_FRAME; row < (this.getHeight() - OUTSIDE_FRAME); row++) {
            for (int column = OUTSIDE_FRAME; column < (this.getWidth()); column++) {
                if (getStationaryTypeAt(column, row) == SquareType.EMPTY) {
                    break;
                }
                if (column == (this.getWidth() - OUTSIDE_FRAME)) {
                    // The row is complete, delete it!
                    this.deleteRow(row);
                }
            }
        }
        this.notifyListeners();
    }

    private void deleteRow(int index) {
        for (int i = index; i > OUTSIDE_FRAME + 1; i--) {
            for (int column = OUTSIDE_FRAME; column < this.getWidth() - OUTSIDE_FRAME; column++) {
                this.setSquareType(column, i, this.getStationaryTypeAt(column, i-1));
            }
        }

        // This is the topmost row, this will be completely empty.
        for (int column = OUTSIDE_FRAME; column < this.getWidth() - OUTSIDE_FRAME; column++) {
            this.setSquareType(column, OUTSIDE_FRAME, SquareType.EMPTY);
        }
    }

    private void makeFallingStationary() {
        for (int i = 0; i < this.falling.getSize(); i++) {
            for (int j = 0; j < this.falling.getSize(); j++) {
                SquareType polyType = this.falling.getPolyTypeAt(i, j);
                if (polyType != SquareType.EMPTY) {
                    this.setSquareType(this.fallingPolyPos.x + i, this.fallingPolyPos.y + j, polyType);
                }
            }
        }
    }

    private void resetFalling() {
        this.falling = null;
        this.fallingPolyPos = null;
    }

    private boolean existFalling() {
        if (this.falling != null) {
            return true;
        }
        return false;
    }

    public Point getSpawnPos() {
        int startx = Math.round(this.getWidth() / 2);
        int starty = OUTSIDE_FRAME;
        return new Point(startx, starty);
    }

    public void updateBoard() {
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }

    private void endGame() {
        this.gameOver = true;
    }

    private void rotate() {
    }


}
