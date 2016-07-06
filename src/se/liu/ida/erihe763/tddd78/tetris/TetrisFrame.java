package se.liu.ida.erihe763.tddd78.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Eric Henziger on 2014-02-16.
 */

public class TetrisFrame extends JFrame {
    
    private Board board;
    private TetrisComponent tetrisComponent;

    public TetrisFrame(Board board) {
        super("ViTetris");
        this.board = board;
        this.createMenus();

        this.tetrisComponent = new TetrisComponent(this.board);

        this.setLayout(new BorderLayout());
        this.add(tetrisComponent, BorderLayout.CENTER);
        this.setKeyBindings();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void createMenus() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem avsluta = new JMenuItem("Avsluta");
        fileMenu.add(avsluta);

        // Add listener for "Avsluta" menu item
        avsluta.addActionListener(ae -> {
            String message = " Vill du verkligen avsluta? ";
            String title = "Bye bye :(";
            int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION)
            {
                // If we choose 'Yes', exit the program immediately. Otherwise do nothing.
                System.exit(0);
            }
        });

        final JMenu viewMenu = new JMenu("View");
        final JMenu windowSize = new JMenu("Window Size");
        final JRadioButtonMenuItem smallSize = new JRadioButtonMenuItem("Small");
        windowSize.add(smallSize);
        final JRadioButtonMenuItem mediumSize = new JRadioButtonMenuItem("Medium");
        mediumSize.setSelected(true);
        windowSize.add(mediumSize);
        final JRadioButtonMenuItem largeSize = new JRadioButtonMenuItem("Large");
        windowSize.add(largeSize);

        // Place all window size buttons in the same group.
        ButtonGroup wsGroup = new ButtonGroup();
        wsGroup.add(smallSize);
        wsGroup.add(mediumSize);
        wsGroup.add(largeSize);

        // Add listener for small window size menu item
        smallSize.addActionListener(ae -> {
            tetrisComponent.setSquareInt(10);
            tetrisComponent.resizeComponent();
            this.pack();
        });

        // Add listener for medium window size menu item
        mediumSize.addActionListener(ae -> {
            tetrisComponent.setSquareInt(20);
            tetrisComponent.resizeComponent();
            this.pack();
        });

        // Add listener for large window size menu item
        largeSize.addActionListener(ae -> {
            tetrisComponent.setSquareInt(30);
            tetrisComponent.resizeComponent();
            this.pack();
        });
        viewMenu.add(windowSize);

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
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

    private Action rotate = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            board.rotate();
        }
    };

    private Action exit = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    private void setKeyBindings() {
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("H"), "moveLeft");
        tetrisComponent.getActionMap().put("moveLeft", moveLeft);

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("L"), "moveRight");
        tetrisComponent.getActionMap().put("moveRight", moveRight);

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("J"), "moveAllWayDown");
        tetrisComponent.getActionMap().put("moveAllWayDown", moveDown);

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "rotate");  // Space key
        tetrisComponent.getActionMap().put("rotate", rotate);

        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");  // Space key
        tetrisComponent.getActionMap().put("exit", exit);

    }
}
