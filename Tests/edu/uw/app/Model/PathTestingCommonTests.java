package edu.uw.app.Model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PathTestingCommonTests {

    public static final int BASIC_POINT_VALUE = 1;
    public static final int BAD_POINT_VALUE = 999;

    /**
     * Sets all points along the set of point to one for
     * testing purposes
     * @param pPoints the points to set to 1
     */
    public static void setPath(final Point[] pPoints, final Board pBoard) {
        for (final Point p : pPoints) pBoard.set(p, BASIC_POINT_VALUE);
    }

    /**
     * Tests the path for the target on the board.
     * @param pTarget the target to test for using summation
     * @param pBoard the board to test for
     */
    public static void testPath(final int pTarget, final Board pBoard) {
        final PathTester lTester = new GamePathTester(pBoard, Integer::sum);
        
        assertTrue(
                lTester.solutionExists(pTarget),
                "tester failed to find the path of length %d"
                        .formatted(pTarget)
        );
        assertFalse(
                lTester.solutionExists(pTarget + 1),
                "tester erroneously said it found a path for length %d"
                        .formatted(pTarget + 1)
        );
    }

    /**
     * sets and tests the path against the board
     * @param pPoints the path to test
     */
    public static void testPath(final Point[] pPoints, final Board pBoard) {
        setPath(pPoints, pBoard);
        testPath(pPoints.length, pBoard);
    }


    /**
     * Fills the board with bad values (999) that should not be summed to
     * @param pBoard the board to fill
     */
    public static void clearBoard(final Board pBoard) {
        final int lBoardSize = pBoard.size();
        for (int row = 0; row < lBoardSize; row++) {
            for (int col = 0; col < lBoardSize; col++) {
                pBoard.set(col, row, BAD_POINT_VALUE);
            }
        }
    }
}
