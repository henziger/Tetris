package se.liu.ida.erihe763.tddd78.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

/**
 * Created by erihe763 on 2014-02-19.
 */
public class TetrisComponent extends JComponent
{
    private Board board;
    private EnumMap colorMap;

    public TetrisComponent(Board board) {
	this.board = board;


	colorMap = new EnumMap<SquareType, Color>(SquareType.class);

	colorMap.put(SquareType.EMPTY, Color.BLACK);
	colorMap.put(SquareType.I, Color.CYAN);
	colorMap.put(SquareType.J, Color.BLUE);
	colorMap.put(SquareType.L, Color.ORANGE);
	colorMap.put(SquareType.O, Color.YELLOW);
	colorMap.put(SquareType.S, Color.GREEN);
	Color purple = new Color(128, 0, 128);
	colorMap.put(SquareType.T, purple);
	colorMap.put(SquareType.Z, Color.RED);
    }

    @Override
    public Dimension getPreferredSize() {
	return super.getPreferredSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;


	for (int i = 0; i < this.board.getHeight(); i++) {
	    for (int j = 0; i < this.board.getWidth(); j++) {
		Color lol = colorMap.get(this.board.getSquareType(i, j))
		g.setColor(lol);
		g.drawRect(i * 10, j * 10, 10, 10);

	    }

	}



}
}
