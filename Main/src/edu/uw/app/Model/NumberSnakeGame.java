package edu.uw.app.Model;

/**
 * Creates the game from the model.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
public class NumberSnakeGame extends AbstractPropertyChangeAdapter implements Game {
    private final BoardHandler fBoardHandler;

    /**
     * Creates the game.
     */
    public NumberSnakeGame() {
        super();
        this.fBoardHandler = new GameBoardHandler(3, Integer::sum);
    }

    @Override
    public void newGame(final int pTarget) {
        this.fBoardHandler.newGame(pTarget);
        this.fPropChSupp.firePropertyChange(PROPERTY_NEW_GAME, null, null);
    }

    @Override
    public void startPath(final int pX, final int pY) {
        this.fBoardHandler.startPath(pX, pY);
    }

    @Override
    public void addToPath(final int pX, final int pY) {
        this.fBoardHandler.addPoint(pX, pY);
    }

    @Override
    public boolean resolvePath() throws IllegalStateException {
        final int[][] oldBoard = this.fBoardHandler.getBoard();
        final boolean validPath = this.fBoardHandler.resolvePath();
        if (validPath) {
            final int[][] newBoard = this.fBoardHandler.getBoard();
            this.fPropChSupp.firePropertyChange(PROPERTY_GOOD_PATH, oldBoard, newBoard);
        } else {
            this.fPropChSupp.firePropertyChange(PROPERTY_BAD_PATH, null, null);
        }
        return validPath;
    }

    @Override
    public void setTarget(final int pTarget) {
        this.fBoardHandler.setTarget(pTarget);
    }

    @Override
    public int getTarget() {
        return this.fBoardHandler.getTarget();
    }

    @Override
    public int getGameBoardSize() {
        return this.fBoardHandler.getBoardSize();
    }

    @Override
    public void setGameBoardSize(final int pSize) {
        this.fBoardHandler.setBoardSize(pSize);
    }
}