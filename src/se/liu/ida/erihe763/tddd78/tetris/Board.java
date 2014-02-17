package se.liu.ida.erihe763.tddd78.tetris;

import java.util.Random;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class Board {
    private SquareType[][] squares;

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


    public static Board generateRandomBoard() {
        Board randBoard = new Board(25, 25);
        SquareType[] types = SquareType.values();
        
        for (int i = 0; i < randBoard.getWidth(); i++) {
            for (int j = 0; j < randBoard.getHeight(); j++) {
                randBoard.setSquareType(i, j, types[new Random().nextInt(types.length -1)]);
            }
        }
        return randBoard;
    }


    public static void main(String[] args) {
        Board b1 = new Board(24, 32);
        System.out.println(b1.squares[0][12]);
    }
}
