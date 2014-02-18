package se.liu.ida.erihe763.tddd78.tetris;

import javax.swing.*;
import java.awt.*;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class TetrisFrame extends JFrame {
    
    private Board board;
    private JTextArea textArea;

    public TetrisFrame(Board board) {
        super("Tetris2014");
        this.board = board;
        this.textArea = new JTextArea(board.getHeight(), board.getWidth());
        this.textArea.setText(TetrisTextView.convertToText(board));
        this.setLayout(new BorderLayout());
        this.add(textArea, BorderLayout.CENTER);

    }

    public void updateTextArea() {
        this.board.updateBoard();
        this.textArea.setText(TetrisTextView.convertToText(this.board));
    }
}
