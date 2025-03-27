package edu.uw.app.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the pathing.
 */
class GamePath implements Path {

    private final List<Point> iPath;
    private boolean iIsValidPath = true;


    public GamePath() {
        super();
        this.iPath = new ArrayList<>();
    }

    public GamePath(final List<Point> pPath) {
        super();
        this.iPath = pPath;
    }

    @Override
    public void add(final Point pPoint) {
        // check if the deltas are greater than 1
        if (this.iIsValidPath && !this.iPath.isEmpty()) {
            final int deltaX = Math.abs(this.iPath.getLast().getX() - pPoint.getX());
            final int deltaY = Math.abs(this.iPath.getLast().getY() - pPoint.getY());
            this.iIsValidPath = deltaX <= 1 && deltaY <= 1;
        }
        this.iPath.add(pPoint);
    }

    @Override
    public boolean isValid() {
        return this.iIsValidPath;
    }

    @Override
    public Point getNextPoint() {
        if (this.iPath.isEmpty()) {
            return null;
        } else {
            return this.iPath.removeFirst();
        }
    }
}
