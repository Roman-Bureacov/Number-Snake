package edu.uw.app.Model;

import java.util.Random;

/**
 * Utility class that does actions with the game board.
 */
final class GameBoardHandler implements BoardHandler {

    private static final Random RANDOMIZER = new Random();
    private static final int MAX_RANDOM_VALUE = 10;
    private static final int MAX_RESET_ATTEMPTS = 5;

    private PathTester fPathTester;
    private Board fBoard;
    private Path fCurrentPath;
    private int fTarget;

    public GameBoardHandler(final int pBoardSize, final PathTotalingAlgorithm pAlg) {
        super();
        this.fBoard = new GameBoard(pBoardSize);
        this.fPathTester = new GamePathTester(this.fBoard, pAlg);
        this.fCurrentPath = new GamePath();
    }

    @Override
    public void newGame(final int pTarget) throws IllegalStateException {
        int attempt = 1;
        this.resetBoard();
        while (!this.fPathTester.solutionExists(pTarget)) {
            attempt++;
            if (attempt > MAX_RESET_ATTEMPTS) {
                throw new IllegalStateException(
                        "Maximum resets reached (%d) on new board attempt for target: %d"
                                .formatted(MAX_RESET_ATTEMPTS, pTarget)
                );
            }
            this.resetBoard();
        }
    }

    /**
     * resets the board with new values from 1-9, inclusive.
     */
    private void resetBoard() {
        final int boardSize = this.fBoard.size();
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                this.fBoard.set(x, y, newRandomInt());
            }
        }
    }

    private static int newRandomInt() {
        // TODO: maybe this could be mutated, the maximum random value?
        return RANDOMIZER.nextInt(MAX_RANDOM_VALUE) + 1;
    }

    @Override
    public void setTarget(final int pTarget) {
        this.fTarget = pTarget;
    }

    @Override
    public int getTarget() {
        return this.fTarget;
    }

    @Override
    public void setBoardSize(final int pSize) {
        this.fBoard = new GameBoard(pSize);
    }

    @Override
    public int getBoardSize() {
        return this.fBoard.size();
    }

    @Override
    public void setAlgorithm(final PathTotalingAlgorithm pAlg) {
        this.fPathTester = new GamePathTester(this.fBoard, pAlg);
    }

    @Override
    public boolean resolvePath() throws IllegalStateException {
        final boolean solutionFound = this.fPathTester.isSolutionPath(this.fCurrentPath, this.fTarget);
        if (solutionFound) {
            final ParsingPath path = this.fCurrentPath.getParsingPath();
            int attempt = 0;
            do {
                attempt++;
                if (attempt > MAX_RESET_ATTEMPTS) {
                    throw new IllegalStateException(
                            "Maximum path resets reached (%d) for finding target %d"
                                    .formatted(MAX_RESET_ATTEMPTS, this.fTarget)
                    );
                }
                for (Point p = path.next(); path.hasNext(); p = path.next()) {
                    this.fBoard.set(p, newRandomInt());
                }

            } while (!this.fPathTester.solutionExists(this.fTarget));
        }
        this.fCurrentPath = null;
        return solutionFound;
    }

    @Override
    public void addPoint(final int pX, final int pY) {
        if (this.fCurrentPath == null) this.startPath(pX, pY);
        else this.fCurrentPath.add(pX, pY);
    }

    @Override
    public void startPath(final int pX, final int pY) {
        this.fCurrentPath = new GamePath(pX, pY);
    }

    @Override
    public int[][] getBoard() {
        return this.fBoard.getBoard();
    }
}
