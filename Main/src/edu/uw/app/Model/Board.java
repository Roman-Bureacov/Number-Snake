package edu.uw.app.Model;

/**
 * Represents the board that has the numbers.
 */
interface Board {
    /**
     * Tests if the path is valid. Should the path be valid and add to
     * the target specified, the game board will replace the points
     * with new values.
     *
     * @param pPath the path to test
     * @param pTarget the target number the path should sum to
     */
    boolean testPath(Path pPath, int pTarget);

    /**
     * Resets the game board with new random numbers.
     */
    void reset();

}
