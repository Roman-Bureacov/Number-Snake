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
     * Returns the size of the board.
     */
    int size();

    /**
     * Resets the game board with new random numbers.
     */
    void reset();

    /**
     * Test the given path with the current board.
     * @return if the path is valid.
     */
    boolean testPath(Path pPath, int pTarget);

    /**
     * Sets the testing path algorithm for the board.
     */
    void setPathTester(PathTester pTester);
}
