package edu.uw.app.Model;

/**
 * Intermediary interface for a class that works and manipulates the game board.
 */
interface BoardHandler {
    /**
     * Tells the handler to create a new game with the board.
     * @throws IllegalStateException when the board fails to find a solution
     * in a reasonable amount of resets.
     */
    void newGame(int pTarget) throws IllegalStateException;

    /**
     * Start a new path on the board.
     */
    void startPath(int pX, int pY);

    /**
     * Add a new point along the path.
     */
    void addPoint(int pX, int pY);

    /**
     * Replaces points along the path with new random values and discards the old path
     * if the current path is a solution for the target.
     * @return true if the current path is a solution, false otherwise
     */
    boolean resolvePath() throws IllegalStateException;

    /**
     * Sets the target value to look for.
     */
    void setTarget(int pTarget);

    /**
     * Gets the current target value.
     */
    int getTarget();

    /**
     * Sets the algorithm to combine numbers with.
     */
    void setAlgorithm(PathTotalingAlgorithm pAlg);

    /**
     * Resizes the board.
     */
    void setBoardSize(int pSize);

    /**
     * Retrieves the board size.
     */
    int getBoardSize();

    /**
     * Retrieves a matrix representation of the board.
     */
    int[][] getBoard();
}
