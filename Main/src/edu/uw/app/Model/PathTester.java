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
    boolean isValid(Path pPath);

    /**
     * Test to see if the path contains the target value based on the algorithm.
     * <br>
     * The first argument of the algorithm the overall value, the second argument is the next value.
     * The idea is how to determine if the target is found in the path. If it were a sum then supply
     * <br>
     * {@code (sum, next) -> sum + next}
     */
    boolean hasTarget(Board pBoard, Path pPath, int pTarget);
}
