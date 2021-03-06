package se.liu.ida.erihe763.tddd78.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by Eric Henziger on 2014-02-19.
 */
public class TetrisComponent extends JComponent implements BoardListener
{
    private Board board;
    private AbstractMap<SquareType, Color> colorMap;

    private int squareInt = 20;

    public TetrisComponent(Board board) {
        this.board = board;
        this.board.addBoardListener(this);

        colorMap = new EnumMap<SquareType, Color>(SquareType.class);
        colorMap.put(SquareType.OUTSIDE, Color.DARK_GRAY);
        colorMap.put(SquareType.EMPTY, Color.BLACK);
        colorMap.put(SquareType.I, Color.CYAN);
        colorMap.put(SquareType.J, Color.BLUE);
        colorMap.put(SquareType.L, Color.ORANGE);
        colorMap.put(SquareType.O, Color.YELLOW);
        colorMap.put(SquareType.S, Color.GREEN);

        // No predefined color for purple exist, so we define our own purple
        // with an RGB value of 128, 0, 128. Hex value #800080.
        Color purple = new Color(128, 0, 128);
        colorMap.put(SquareType.T, purple);
        colorMap.put(SquareType.Z, Color.RED);
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();
        // Size is based on number of blocks.
        return new Dimension(this.board.getWidth() * squareInt, this.board.getHeight() * squareInt);
    }

    public void resizeComponent() {
        super.setPreferredSize(new Dimension(this.board.getWidth() * squareInt, this.board.getHeight() * squareInt));
    }



    /**
     * If we get notified that the board has been changed,we
     * call the repaint method that calls the paintComponent method.
     */
    public void boardChanged() {
        this.repaint();
    }


    /**
     * Paints the board. The board har background color black.
     * For all other squares we look in the colorMap to get
     * an appropriate color. Every square is then painted
     * using the fillRect method.
     * @param g Magic graphics component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());


        for (int i = 0; i < this.board.getWidth(); i++) {
            for (int j = 0; j < this.board.getHeight(); j++) {
                g2d.setColor(colorMap.get(this.board.getSquareType(i, j)));
                g2d.fillRect(i * squareInt, j * squareInt, squareInt, squareInt);

            }
        }
    }

    public void setSquareInt(int squareInt) {
        this.squareInt = squareInt;
    }

}
