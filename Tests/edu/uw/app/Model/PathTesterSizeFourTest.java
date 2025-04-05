package edu.uw.app.Model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PathTesterSizeFourTest {

    private final Board iBoard = new GameBoard(4);
    private final PathTester iTester = new GamePathTester(
            this.iBoard, Integer::sum);

    @BeforeEach
    public void setup() {
        // make all boards start with 0s so that it's easier to test paths
        this.clearBoard();
    }

    @Test
    public void testSPath() {
        this.iBoard.set(1, 2, 1);
        this.iBoard.set(2, 3, 1);
        this.iBoard.set(2, 2, 1);
        this.iBoard.set(2, 1, 1);
        this.iBoard.set(3, 2, 1);
        this.testPath(5);
    }


    @Test
    public void testLongSPath() {
        this.iBoard.set(1, 2, 1);
        this.iBoard.set(2, 3, 1);
        this.iBoard.set(2, 2, 1);
        this.iBoard.set(2, 1, 1);
        this.iBoard.set(2, 0, 1);
        this.iBoard.set(3, 1, 1);
        this.testPath(6);
    }

    @Test
    public void testWideLongSPath() {
        final Point[] lPoints = new GamePoint[] {
                new GamePoint(0, 2),
                new GamePoint(1, 3),
                new GamePoint(1, 2),
                new GamePoint(1, 1),
                new GamePoint(2, 0),
                new GamePoint(3, 1),
        };
        this.setPath(lPoints);
        this.testPath(lPoints.length);
    }

    @Test
    public void testBigCPath() {
        final Point[] lPoints = new GamePoint[] {
                new GamePoint(2, 0),
                new GamePoint(1, 0),
                new GamePoint(0, 1),
                new GamePoint(0, 2),
                new GamePoint(1, 3),
                new GamePoint(2, 3),
        };
        this.testPath(lPoints);
    }

    private void clearBoard() {
        final int lBoardSize = this.iBoard.size();
        final int lBadValue = 999;
        for (int row = 0; row < lBoardSize; row++) {
            for (int col = 0; col < lBoardSize; col++) {
                this.iBoard.set(col, row, lBadValue);
            }
        }
    }

    /**
     * Sets all points along the set of point to one for
     * testing purposes
     * @param pPoints the points to set to 1
     */
    private void setPath(final Point[] pPoints) {
        for (final Point p : pPoints) this.iBoard.set(p, 1);
    }

    private void testPath(final int pTarget) {
        assertTrue(
                this.iTester.solutionExists(pTarget),
                "tester failed to find the path of length %d"
                        .formatted(pTarget)
        );
        assertFalse(
                this.iTester.solutionExists(pTarget + 1),
                "tester erroneously said it found a path for length %d"
                        .formatted(pTarget + 1)
        );
    }

    private void testPath(final Point[] pPoints) {
        this.setPath(pPoints);
        this.testPath(pPoints.length);
    }
}
