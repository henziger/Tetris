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

    public int getWidth() { return this.polyomino.length; }

    public int getHeight() { return this.polyomino[0].length; }
}
