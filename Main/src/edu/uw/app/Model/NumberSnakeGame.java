package edu.uw.app.Model;

/**
 * Creates the game from the model.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
class NumberSnakeGame extends AbstractPropertyChangeAdapter implements Game {
    private final Board fBoard;
    private int fTarget;
    private Path fCurrentPath;
    private PathTotalingAlgorithm fAlg;

    /**
     * Creates the game.
     */
    public NumberSnakeGame() {
        super();
        this.fBoard = new GameBoard(3);
    }

    @Override
    public void newGame(final int pTarget) {
        this.fBoard.newBoard(pTarget, fAlg);
        this.fTarget = pTarget;
        this.fPropChSupp.firePropertyChange(PROPERTY_NEW_GAME, null, null);
    }

    @Override
    public void startPath(final int pX, final int pY) {
        this.fCurrentPath = new GamePath();
    }

    @Override
    public void addToPath(final int pX, final int pY) {
        if (this.fCurrentPath == null) this.startPath(pX, pY);
        else this.fCurrentPath.add(pX, pY);
    }

    @Override
    public boolean resolvePath() throws IllegalStateException {
        final int[][] oldBoard = this.fBoard.getBoard();
        final boolean validPath = PathTester.testPath(this.fBoard, this.fCurrentPath, this.fTarget, this.fAlg);
        final int[][] newBoard = this.fBoard.getBoard();
        if (validPath) {
            this.fPropChSupp.firePropertyChange(PROPERTY_GOOD_PATH, oldBoard, newBoard);
        } else {
            this.fPropChSupp.firePropertyChange(PROPERTY_BAD_PATH, null, this.fCurrentPath);
        }
        this.fCurrentPath = null;
        return validPath;
    }

    @Override
    public void setTarget(final int pTarget) {
        this.fTarget = pTarget;
    }

    @Override
    public int getGameBoardSize() {
        return this.fBoard.size();
    }

    @Override
    public void setGameBoardSize(final int pSize) {
        this.fBoard.setSize(pSize);
    }
}