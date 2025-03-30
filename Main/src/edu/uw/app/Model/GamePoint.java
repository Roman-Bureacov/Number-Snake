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

    @Override
    public boolean equals(final Object pOther) {
        return pOther instanceof final Point p
                && p.getX() == this.getX()
                && p.getY() == this.getY();
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.fPointX, this.fPointY);
    }
}
