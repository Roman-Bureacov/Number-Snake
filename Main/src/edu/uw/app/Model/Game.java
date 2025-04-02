package edu.uw.app.Model;

/**
 * Interface specifying the game.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
public interface Game {

    /**
     * Starts a new game with the target specified.
     * @param pTarget the target the new game should start with
     */
    void newGame(int pTarget);

    /**
     * Starts a new path on the board at the specified coordinates.
     * @param pX the x-coordinate, 0-indexed
     * @param pY the y-coordinate, 0-indexed
     */
    void startPath(int pX, int pY);

    /**
     * Adds a point to the new path. If there is no new path created,
     * the game will create a new path at this point.
     * @param pX the x-coordinate to add, 0-indexed
     * @param pY the y-coordinate to add, 0-indexed
     */
    void addToPath(int pX, int pY);

    /**
     * Resolves the path. If the points on the entire path resolve to the target set,
     * then the points on the board are removed and replaced with new values.
     * Path is always destroyed when resolved, regardless if successful or not.
     * @return true if the path resolves successfully, false otherwise.
     */
    boolean resolvePath();

    /**
     * Sets the target that the path should to resolve to.
     * @param pTarget the target value
     */
    void setTarget(int pTarget);

    /**
     * Gets the target that the path should resolve to.
     * @return the target
     */
    int getTarget();

    /**
     * Sets the size of the game board.
     * @param pSize the new size for the game board.
     */
    void setGameBoardSize(int pSize);

    /**
     * Gets the current size of the game board.
     * @return the size of the current game board.
     */
    int getGameBoardSize();
}
