package se.liu.ida.erihe763.tddd78.tetris;

/**
 * Created by erihe763 on 2014-02-11.
 */
public enum SquareType
{
    /**
     * Types of squares that may be present on the board.
     * OUTSIDE is for blocks that surround the playable area of the board.
     * EMPTY is for squares that is currently not populated by blocks.
     * I, O, T, S, Z, J, L are types of blocks that may populate the board
     * and represent the seven common tetromino types. (http://en.wikipedia.org/wiki/Tetromino)
     */
    OUTSIDE, EMPTY, I, O, T, S, Z, J, L
}
