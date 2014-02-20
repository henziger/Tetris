package se.liu.ida.erihe763.tddd78.tetris;

/**
 * Created by erihe763 on 2014-02-16.
 */

public final class BoardTest {

    private BoardTest() {
    }

    public static void main(String[] args) {

        int boardWwidth = 20;
        int boardHeight = 30;
        Board b1 = new Board(boardWwidth, boardHeight);
        TetrisFrame frame = new TetrisFrame(b1);
        frame.pack();
        frame.setVisible(true);

        while(true) {
            b1.updateBoard();
        }
    }
}
