package edu.uw.app.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathTesterSizeFiveTest {

    private final static int SIZE = 5;

    private final Board iBoard = new GameBoard(SIZE);

    @BeforeEach
    public void clearBoard() {
        PathTestingCommonTests.clearBoard(this.iBoard);
    }

    @Test
    public void testWorstCaseBoard() {
        // set bogus paths
        this.iBoard.set(1, 0, PathTestingCommonTests.BASIC_POINT_VALUE);
        this.iBoard.set(1, 1, PathTestingCommonTests.BASIC_POINT_VALUE);
        this.iBoard.set(2, 1, PathTestingCommonTests.BASIC_POINT_VALUE);

        this.iBoard.set(0, 4, PathTestingCommonTests.BASIC_POINT_VALUE);

        this.iBoard.set(1, 3, PathTestingCommonTests.BASIC_POINT_VALUE);
        this.iBoard.set(0, 4, PathTestingCommonTests.BASIC_POINT_VALUE);
        this.iBoard.set(1, 4, PathTestingCommonTests.BASIC_POINT_VALUE);

        // actual path to test
        final int lTarget = 10;
        this.iBoard.set(3, 4, lTarget / 2);
        this.iBoard.set(4, 4, lTarget / 2);

        PathTestingCommonTests.testPath(lTarget, this.iBoard);
    }
}
