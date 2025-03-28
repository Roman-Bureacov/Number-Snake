package edu.uw.app.Model;

import java.util.Random;

/**
 * Class that represents the game board.
 */
class GameBoard implements Board {

    private static final Random RANDOMIZER = new Random();
    private static final int MAX_VALUE = 10;

    private final int[][] fGameBoard;
    private PathTester fPathTester;

    /**
     * Creates a new, square game board with the specified size.
     */
    public GameBoard(final int pSize) {
        // default path tester will just sum values at points
        this(pSize, new GamePathTester(Integer::sum));
    }

    /**
     * Creates a new, square game board with the specified size
     * and path tester.
     */
    public GameBoard(final int pSize, final PathTester pTester) {
        super();
        this.fGameBoard = new int[pSize][pSize];
        this.fPathTester = pTester;
    }

    @Override
    public boolean testPath(final Path pPath, final int pTarget) {
        return this.fPathTester.isValid(pPath)
                && this.fPathTester.hasTarget(
                        this, pPath, pTarget
                );
    }

    @Override
    public void reset() {
        final int boardSize = this.fGameBoard.length;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                this.fGameBoard[row][col] = RANDOMIZER.nextInt(MAX_VALUE) + 1;
            }
        }
    }

    @Override
    public int get(final Point pPoint) {
        return this.get(pPoint.getX(), pPoint.getY());
    }

    @Override
    public int get(final int pX, final int pY) {
        return this.fGameBoard[pY][pX];
    }

    @Override
    public int size() {
        return this.fGameBoard.length;
    }

    @Override
    public void setPathTester(final PathTester pTester) {
        this.fPathTester = pTester;
    }
}
