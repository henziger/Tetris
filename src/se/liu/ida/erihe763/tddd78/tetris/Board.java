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
    private boolean falling;
    private Poly fallingPoly;
    private Point fallingPolyPos;


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
        return this.squares[x][y];
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
        if (this.falling) {
            this.descendPoly();
        }
        else {
            this.spawnPoly();
        }
    }

    private final Timer clockTimer = new Timer(500, doOneStep);


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

    public void spawnPoly() {

    }

    public Point getSpawnPos() {
        int startx = Math.round(this.getWidth() / 2);
        int starty = this.OUTSIDE_FRAME + 1;
        Point spawnPos = new Point(startx, starty);
        return spawnPos;
    }

    public void updateBoard() {
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }


}
