package edu.uw.app.Model;

/**
 * Interface for a path testing class to check for valid pathing.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
interface PathTester {
    /**
     * Test to see if the pathing is valid.
     */
    boolean isValidPath(Path pPath);

    /**
     * Test to see if the path contains the target value based on the algorithm.
     * <br>
     * The first argument of the algorithm the overall value, the second argument is the next value.
     * The idea is how to determine if the target is found in the path. If it were a sum then supply
     * <br>
     * {@code (sum, next) -> sum + next}
     */
    boolean pathHasTarget(Board pBoard, Path pPath, int pTarget);

    /**
     * Tests if a given board contains a solution for the target.
     * @param pBoard the board to look for the solution within
     * @param pTarget the target to look for
     */
    boolean solutionExists(Board pBoard, int pTarget);

    /**
     * Test the given path with the current board. Clears the points if the path is valid.
     * @return if the path is valid.
     */
    boolean resolvePath(Board pBoard, Path pPath, int pTarget);

    /**
     * resets the board with new values.
     */
    void resetBoard(Board pBoard);

}
