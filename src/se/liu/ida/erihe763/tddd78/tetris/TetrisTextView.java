package se.liu.ida.erihe763.tddd78.tetris;


/**
 * Created by erihe763 on 2014-02-16.
 */

public class TetrisTextView {


    public static String convertToText(Board b) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {

                switch (b.getSquareType(i, j)) {
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
