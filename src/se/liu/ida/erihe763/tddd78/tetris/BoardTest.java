package se.liu.ida.erihe763.tddd78.tetris;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class BoardTest {
    public static void main(String[] args) {

        // Generate and print three random boards.
        Board rb1 = Board.generateRandomBoard();
        Board rb2 = Board.generateRandomBoard();
        Board rb3 = Board.generateRandomBoard();

        System.out.println(TetrisTextView.convertToText(rb1));
        System.out.println(TetrisTextView.convertToText(rb2));
        System.out.println(TetrisTextView.convertToText(rb3));
    }
}
