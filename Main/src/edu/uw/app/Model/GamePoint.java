package edu.uw.app.Model;

/**
 * Class that represents a point on the game board.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
class GamePoint implements Point {

    private final int fPointX;
    private final int fPointY;

    /**
     * Creates a new point with the specified points.
     */
    public GamePoint(final int pPointX, final int pPointY) {
        super();

        this.fPointX = pPointX;
        this.fPointY = pPointY;
    }

    @Override
    public int getX() {
        return this.fPointX;
    }

    @Override
    public int getY() {
        return this.fPointY;
    }
}
