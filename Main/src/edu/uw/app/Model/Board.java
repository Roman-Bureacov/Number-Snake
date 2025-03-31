package edu.uw.app.Model;

/**
 * Represents the board that has the numbers.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
interface Board {

    /**
     * Returns the value at the specified point.
     */
    int get(Point pPoint);

    /**
     * Returns the value at the specified coordinate.
     */
    int get(int pX, int pY);

    /**
     * Sets the coordinate at the board to the value.
     */
    void set(int pX, int pY, int pToInt);

    /**
     * Sets the point at the board to the value.
     */
    void set(Point pPoint, int pToInt);

    /**
     * Returns the size of the board.
     */
    int size();

    /**
     * Returns a copy representation for the current board.
     */
    int[][] getBoard();

}
