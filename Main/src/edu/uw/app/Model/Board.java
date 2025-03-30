package edu.uw.app.Model;

/**
 * Represents the board that has the numbers.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
interface Board {

    /** Property name for if the board failed to find a solution. */
    String PROPERTY_NO_SOLUTION = "there was no solution for target";

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
     * Resizes the board. Size must be at least 3 or greater.
     */
    void setSize(int pSize);

    /**
     * Resets the game board with new random numbers, creating
     * a new board for a new game.
     * @throws IllegalStateException thrown when the board fails to find a solution for the target
     */
    void newBoard(int pTarget, PathTester pTester) throws IllegalStateException;

    /**
     * Returns a copy representation for the current board.
     */
    int[][] getBoard();
}
