package edu.uw.app.Model;

/**
 * Class that represents a point on the game board.
 */
class GamePoint implements Point {

    private final int iPointX;
    private final int iPointY;

    /**
     * Creates a new point with the specified points.
     */
    public GamePoint(final int pPointX, final int pPointY) {
        super();

        this.iPointX = pPointX;
        this.iPointY = pPointY;
    }

    @Override
    public int getX() {
        return this.iPointX;
    }

    @Override
    public int getY() {
        return this.iPointY;
    }
}
