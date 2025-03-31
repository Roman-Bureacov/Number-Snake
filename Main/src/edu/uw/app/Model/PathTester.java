package edu.uw.app.Model;

/**
 * Provides the interface for testing paths.
 */
interface PathTester {
    /**
     * Tests if the path is of valid format.
     * @param pPath the path to test
     * @return true if the path has points going to adjacent points
     * and no intersecting points
     */
    boolean isValidPathFormat(final Path pPath);

    /**
     * Tests if the path has the target
     * @param pPath the path to test
     * @param pTarget the target to test for
     * @return true if the path has the target, false otherwise
     */
    boolean pathHasTarget(final Path pPath, int pTarget);

    /**
     * Test if the board has the target.
     * @param pTarget the target to test for
     * @return false if no path for the target exists
     */
    boolean solutionExists(int pTarget);

    /**
     * Tests if the path is both valid and has the target.
     * @param pPath the path to test
     * @param pTarget the target to test for
     * @return if both the path is valid and has the target
     */
    default boolean isSolutionPath(final Path pPath, final int pTarget) {
        return this.isValidPathFormat(pPath) && this.pathHasTarget(pPath, pTarget);
    }
}
