package se.liu.ida.erihe763.tddd78.tetris;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class Poly {
    
    private SquareType[][] polyomino;

    public Poly(SquareType[][] polyomino) {
	if (polyomino.length != polyomino[0].length) {
	    //throw new TetrisException("Poly size must be quadratic");
	}
        this.polyomino = polyomino;
    }

    public SquareType getSquareType(int x, int y) {
        return this.polyomino[y][x];
    }

    public int getSize() {
        return this.polyomino.length;
    }
}
