package se.liu.ida.erihe763.tddd78.tetris;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class TetrominoMaker {

    private int numberOfTypes = 7;

    public int getNumberOfTypes() {
        return this.numberOfTypes;
    }

    public Poly getPoly(int n) {
        switch(n) {
        case 0:
            return getI();
        case 1:
            return getJ();
        case 2:
            return getL();
        case 3:
            return getO();
        case 4:
            return getS();
        case 5:
            return getT();
        case 6:
            return getZ();
        default:
            throw new IndexOutOfBoundsException("Invalid block type index: " + n);
        }
    }

    public Poly getI() {
        return new Poly(new SquareType[][] {
                { SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.I, SquareType.EMPTY, SquareType.EMPTY }
            });
    }

    public Poly getJ() {
        return new Poly(new SquareType[][] {
                { SquareType.EMPTY, SquareType.EMPTY, SquareType.J, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.EMPTY, SquareType.J, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.J, SquareType.J, SquareType.EMPTY }
            });
    }

    public Poly getL() {
        return new Poly(new SquareType[][] {
                { SquareType.EMPTY, SquareType.L, SquareType.EMPTY, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.L, SquareType.EMPTY, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.L, SquareType.L, SquareType.EMPTY }
            });
    }

    public Poly getO() {
        return new Poly(new SquareType[][] {
                { SquareType.EMPTY, SquareType.O, SquareType.O },
                { SquareType.EMPTY, SquareType.O, SquareType.O },
                // TODO: Can this be removed?
                { SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY }
            });
    }

    public Poly getS() {
        return new Poly(new SquareType[][] {
                { SquareType.EMPTY, SquareType.S, SquareType.S },
                { SquareType.S, SquareType.S, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY }
            });
    }

    public Poly getT() {
        return new Poly(new SquareType[][] {
                { SquareType.EMPTY, SquareType.T, SquareType.EMPTY },
                { SquareType.T, SquareType.T, SquareType.T },
                { SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY }
            });
    }

    public Poly getZ() {
        return new Poly(new SquareType[][] {
                { SquareType.Z, SquareType.Z, SquareType.EMPTY },
                { SquareType.EMPTY, SquareType.Z, SquareType.Z },
                { SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY }
            });
    }
}
