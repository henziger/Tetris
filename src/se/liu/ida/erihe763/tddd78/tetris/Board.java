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


    protected SquareType getSquareType(int x, int y) {
        SquareType fallingType = this.getFallingType(x, y);
        if (fallingType != SquareType.EMPTY) {
            return fallingType;
        }
        else {
            return this.getStationaryType(x, y);
        }
    }


    private SquareType getStationaryType(int x, int y) {
        return this.squares[x][y];
    }


    private SquareType getFallingType(int x, int y) {
        if (this.falling == null) {
            return SquareType.EMPTY;
        }
        for (int i = 0; i < this.falling.getSize(); i++) {
            for (int j = 0; j< this.falling.getSize(); j++) {
                if (x == (this.fallingPolyPos.x + i) && y == (this.fallingPolyPos.y + j)) {
                    return this.falling.getSquareType(i, j);
                }
            }
        }
        return SquareType.EMPTY;
    }


    private void setSquareType(int x, int y, SquareType st) {
        this.squares[x][y] = st;
        this.notifyListeners();
    }


    private final Action doOneStep = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    };
    private final Timer clockTimer = new Timer(TICK_DELAY, doOneStep);


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


    public void addBoardListener(BoardListener bl) {
        this.listenerList.add(bl);
    }

    private void notifyListeners() {
        for (BoardListener boardListener : listenerList) {
            boardListener.boardChanged();
        }
    }


    private void randomizeBoard() {
        SquareType[] types = SquareType.values();
        
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getHeight() - 1; j++) {
                this.setSquareType(i, j, types[new Random().nextInt(types.length - 1)]);
            }
        }

        this.notifyListeners();
    }


    private void clearBoard() {
        for (int i = 1; i < this.getWidth() - 1; i++) {
            for (int j = 1; j < this.getHeight() - 1; j++) {
                this.setSquareType(i, j, SquareType.EMPTY);
            }
        }
    }

    /**
     * @return A random type of polyomino.
     */
    public Poly spawnPoly() {
        TetrominoMaker tetrominoMaker = new TetrominoMaker();
        return tetrominoMaker.getPoly(new Random().nextInt(tetrominoMaker.getNumberOfTypes()));
    }


    /**
     * For all methods that wish to move the falling polyomino we assert two things:
     * 1) It actually exists a falling polyomino.
     * 2) The position that we wish to move the polyomino to is valid,
     *    ie not occupied by other blocks.
     * If movement occured we must notify all listeners to update their
     * representation of the board.
     */
    public void descendPoly() {
	if (this.existFalling() && validatePolyPosition(0, 1)) {
	            this.fallingPolyPos.y += 1;
	            this.notifyListeners();
	}
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

    public void moveAllWayDown() {
	// As long as the block can be moved one step down, do it!
	// This is nice when we don't want to wait for a block to
	// slowly descend all the way down by itself.
        while (this.existFalling() && validatePolyPosition(0, 1)) {
            this.fallingPolyPos.y += 1;
        }
        this.notifyListeners();
    }


    /**
     * Verify that the non-empty squares in the falling block doesn't
     * interfere with a stationary block. The offset params perform a
     * check on the blocks x,y squares from the falling blocks current
     * position. This is used when determining if we may move a block
     * in any direction.
     *
     * @param xoffset Check blocks that are [xoffset] blocks away horizontally.
     * @param yoffset Check blocks that are [yoffset] blocks away vertically.
     * @return true if no interfering blocks are found, otherwise false.
     */
    private boolean validatePolyPosition(int xoffset, int yoffset) {
        for (int i = 0; i < this.falling.getSize(); i++) {
            for (int j = 0; j < this.falling.getSize(); j++) {
                if (this.falling.getSquareType(i, j) != SquareType.EMPTY &&
                        !this.isSquareEmpty(this.fallingPolyPos.x + i + xoffset,
                                this.fallingPolyPos.y + j + yoffset)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSquareEmpty(int x, int y) {
        if (this.getStationaryType(x, y) == SquareType.EMPTY) {
            return true;
        }
        return false;
    }


    /**
     * Iterate over every row, if all squares in a row are non-empty it means
     * that the row is complete and may be removed.
     */

    private void removeCompleteRows() {
        for (int row = OUTSIDE_FRAME; row < (this.getHeight() - OUTSIDE_FRAME); row++) {
            for (int column = OUTSIDE_FRAME; column < (this.getWidth()); column++) {
                if (getStationaryType(column, row) == SquareType.EMPTY) {
                    break;
                }
                if (column == (this.getWidth() - OUTSIDE_FRAME)) {
		    // We got to the last column in the row without finding an empty square.
                    // The row is complete, delete it!
                    this.deleteRow(row);
                }
            }
        }
        this.notifyListeners();
    }


    /**
     * To remove a row and lower all rows above that one we iterate over
     * every row and check the value of the block above. The topmost row
     * have no row above it (except the outside row) so for that one we
     * simply create a completely empty row.
     * @param index The index number of the row that should be removed.
     */
    private void deleteRow(int index) {
        for (int i = index; i > OUTSIDE_FRAME + 1; i--) {
            for (int column = OUTSIDE_FRAME; column < this.getWidth() - OUTSIDE_FRAME; column++) {
                this.setSquareType(column, i, this.getStationaryType(column, i - 1));
            }
        }

        // This is the topmost row, this will be completely empty.
        for (int column = OUTSIDE_FRAME; column < this.getWidth() - OUTSIDE_FRAME; column++) {
            this.setSquareType(column, OUTSIDE_FRAME, SquareType.EMPTY);
        }
    }


    /**
     * Make all the non-empty squares in the falling Poly write to
     * the board and make the squares stationary.
     */
    private void makeFallingStationary() {
        for (int i = 0; i < this.falling.getSize(); i++) {
            for (int j = 0; j < this.falling.getSize(); j++) {
                SquareType polyType = this.falling.getSquareType(i, j);
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
	// IDEA warns that the check for null is not inside an if, ignore it.
        if (this.falling != null) {
            return true;
        }
        return false;
    }

    private Point getSpawnPos() {
	int startx = Math.round((float)this.getWidth() / 2 - (float)this.falling.getSize() / 2);
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

    // FUTURE: Add feature to rotate blocks.
    private void rotate() {
    }


}
