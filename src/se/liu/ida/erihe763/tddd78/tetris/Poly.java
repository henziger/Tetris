package se.liu.ida.erihe763.tddd78.tetris;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class Poly {
    
    private SquareType[][] polyomino;

    public Poly(SquareType[][] polyomino)  {
        this.polyomino = polyomino;
    }

    public SquareType getSquareType(int x, int y) {
        return this.polyomino[y][x];
    }

    public Poly rotate(boolean clockwise) {
        Poly newPoly;
        if (clockwise) {
            newPoly = rotateRight();
        } else {
            newPoly = rotateLeft();
        }
        return newPoly;
    }

    private Poly rotateRight() {
        int size = polyomino.length;
        Poly newPoly = new Poly(new SquareType[size][size]);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                newPoly.polyomino[c][size-1-r] = this.polyomino[r][c];
            }
        }
        return newPoly;
    }

    private Poly rotateLeft() {
        int size = polyomino.length;
        Poly newPoly = new Poly(new SquareType[size][size]);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                newPoly.polyomino[r][c] = this.polyomino[c][size-1-r];
            }
        }
        return newPoly;
    }

    public int getWidth() { return this.polyomino.length; }

    public int getHeight() { return this.polyomino[0].length; }
}
