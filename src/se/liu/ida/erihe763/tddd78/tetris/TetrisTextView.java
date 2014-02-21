package se.liu.ida.erihe763.tddd78.tetris;


/**
 * Created by erihe763 on 2014-02-16.
 */

public final class TetrisTextView {


    private TetrisTextView() {
    }

    public static String convertToText(Board board) {

        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < board.getHeight(); row++) {
            for (int column = 0; column < board.getWidth(); column++) {

                switch (board.getSquareType(column, row)) {
                    case OUTSIDE:
                        builder.append("§");
                        break;
                    case EMPTY:
                        builder.append("-");
                        break;
                    case I:
                        builder.append("I");
                        break;
                    case J:
                        builder.append("J");
                        break;
                    case L:
                        builder.append("L");
                        break;
                    case O:
                        builder.append("O");
                        break;
                    case T:
                        builder.append("T");
                        break;
                    case S:
                        builder.append("S");
                        break;
                    case Z:
                        builder.append("Z");
                        break;
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
