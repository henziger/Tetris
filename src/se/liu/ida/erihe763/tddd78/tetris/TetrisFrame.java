package se.liu.ida.erihe763.tddd78.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by erihe763 on 2014-02-16.
 */

public class TetrisFrame extends JFrame {
    
    private Board board;
    private TetrisComponent tetrisComponent;

    public TetrisFrame(Board board) {
        super("Tetris2014");
        this.board = board;
        this.createMenus();

        this.tetrisComponent = new TetrisComponent(this.board);

        this.setLayout(new BorderLayout());
        this.add(tetrisComponent, BorderLayout.CENTER);
        this.setKeyBindings();

    }

    public void createMenus() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem avsluta = new JMenuItem("Avsluta");
        fileMenu.add(avsluta);

        // Add listener for "Avsluta" menu item
        avsluta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = " Vill du verkligen avsluta? ";
                String title = "Bye bye :(";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION)
                {
		    // If we choose Yes, exit the program immediately. Otherwise do nothing.
                    System.exit(0);
                }
            }
        });

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }


    /**
     * Key binding actions, vi style.
     */
    private Action moveLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveLeft();
        }
    };

    private Action moveRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveRight();
        }
    };

    private Action moveDown = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.moveAllWayDown();
        }
    };

    private void setKeyBindings() {
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("H"), "moveLeft");
        tetrisComponent.getActionMap().put("moveLeft", moveLeft);

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("L"), "moveRight");
        tetrisComponent.getActionMap().put("moveRight", moveRight);

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("J"), "moveAllWayDown");
        tetrisComponent.getActionMap().put("moveAllWayDown", moveDown);
    }
}
