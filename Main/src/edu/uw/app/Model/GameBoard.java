package edu.uw.app.Model;

import java.util.Random;

/**
 * Class that represents the game board.
 */
class GameBoard implements Board {

    private static final Random RANDOMIZER = new Random();
    private static final int MAX_VALUE = 10;

    private final int[][] iGameBoard;

    /**
     * Creates a new, square game board with the specified size.
     */
    public GameBoard(final int pSize) {
        super();

        this.iGameBoard = new int[pSize][pSize];
    }

    @Override
    public boolean testPath(final Path pPath, final int pTarget) {
        if (!pPath.isValid()) {
            return false;
        }

        int sum = 0;
        Point point;
        while ((point = pPath.getNextPoint()) != null) {
            final int x = point.getX();
            final int y = point.getY();

            final int boardSize = this.iGameBoard.length;
            if (!(0 <= x && x < boardSize
                && 0 <= y && y < boardSize)) {
                return false;
            }

            sum += this.iGameBoard[x][y];

            if (sum > pTarget) {
                return false;
            }
        }

        return sum == pTarget;
    }

    @Override
    public void reset() {
        final int boardSize = this.iGameBoard.length;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                this.iGameBoard[row][col] = RANDOMIZER.nextInt(MAX_VALUE) + 1;
            }
        }
    }

}
