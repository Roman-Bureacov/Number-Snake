package edu.uw.app.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PathTesterGeneralBoardTests {

    private Board iBoard;

    @BeforeEach
    public void setup() {
        // to prevent erroneous tests being accidentally written
        this.iBoard = null;
    }

    @Test
    public void testVeryLargeBoard() {
        final int lVeryLargeSize = 10;
        this.iBoard = new GameBoard(lVeryLargeSize);
        PathTestingCommonTests.fillBoard(this.iBoard, 1);

        final int lTarget = lVeryLargeSize * lVeryLargeSize;
        this.iBoard.set(lVeryLargeSize - 2, lVeryLargeSize - 1, lTarget / 2);
        this.iBoard.set(lVeryLargeSize - 1, lVeryLargeSize - 1, lTarget / 2);
        PathTestingCommonTests.testTarget(lTarget, this.iBoard);
    }
}
