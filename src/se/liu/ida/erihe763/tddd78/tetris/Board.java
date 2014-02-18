package se.liu.ida.erihe763.tddd78.tetris;

import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class Board {

    private SquareType[][] squares;
    private Poly falling;
    private int fallingX;
    private int fallingY;


    public Board(int width, int height) {
        squares = new SquareType[width][height];

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
    }


    private final Action doOneStep = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            randomizeBoard();
        }
    };

    private final Timer clockTimer = new Timer(500, doOneStep);


    public void randomizeBoard() {
        SquareType[] types = SquareType.values();
        
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                this.setSquareType(i, j, types[new Random().nextInt(types.length - 1)]);
            }
        }
    }

    public void updateBoard() {
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }


}
