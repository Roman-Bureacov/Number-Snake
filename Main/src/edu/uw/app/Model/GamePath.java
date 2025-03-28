package edu.uw.app.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.naming.OperationNotSupportedException;

/**
 * Class that handles the pathing.
 */
class GamePath implements Path {

    private final List<Point> fPath;
    private boolean fIsValidPath = true;


    public GamePath() {
        super();
        this.fPath = new ArrayList<>();
    }

    @Override
    public void add(final Point pPoint) {
        // check if the deltas are greater than 1
        if (this.fIsValidPath && !this.fPath.isEmpty()) {
            final int deltaX = Math.abs(this.fPath.getLast().getX() - pPoint.getX());
            final int deltaY = Math.abs(this.fPath.getLast().getY() - pPoint.getY());
            this.fIsValidPath = deltaX <= 1 && deltaY <= 1;
        }
        this.fPath.add(pPoint);
    }

    @Override
    public ParsingPath getParsingPath() {
        return new GameParsingPath(this.fPath);
    }

    private static class GameParsingPath implements ParsingPath {
        private final List<Point> fPoints;
        private int fIndex;

        GameParsingPath(final List<Point> pList) {
            super();
            this.fPoints = pList;
        }

        @Override
        public Point get(final int pIndex) {
            return this.fPoints.get(pIndex);
        }

        @Override
        public int size() {
            return this.fPoints.size();
        }

        @Override
        public boolean hasNext() {
            return this.fIndex < this.fPoints.size();
        }

        @Override
        public Point next() {
            final Point point = this.fPoints.get(this.fIndex);
            this.fIndex++;
            return point;
        }

        @Override
        public void resetParse() {
            this.fIndex = 0;
        }

    }
}
