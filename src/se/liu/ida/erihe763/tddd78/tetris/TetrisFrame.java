package se.liu.ida.erihe763.tddd78.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
	this.createMenus();


    }

    public void updateTextArea() {
        this.board.updateBoard();
        this.textArea.setText(TetrisTextView.convertToText(this.board));
    }

    public void createMenus() {
	final JMenu file = new JMenu("File");
	final JMenuItem avsluta = new JMenuItem("Avsluta");
	file.add(avsluta);


	// Add listener for "Avsluta" menu item
	avsluta.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String message = " Vill du verkligen avsluta? ";
		String title = "Bye bye :(";
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION)
		{
		    System.exit(0);
		}
	    }
	});

	final JMenuBar bar = new JMenuBar();
	bar.add(file);
	this.setJMenuBar(bar);


}
}
