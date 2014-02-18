package se.liu.ida.erihe763.tddd78.tetris;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class BoardTest {
    public static void main(String[] args) {

        Board b1 = new Board(20, 30);
        b1.randomizeBoard();
        TetrisFrame frame = new TetrisFrame(b1);
        frame.pack();
        frame.setVisible(true);

        while(true) {
            frame.updateTextArea();
        }

    }
}
