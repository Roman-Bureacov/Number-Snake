package edu.uw.app.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to test for valid paths.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
class GamePathTester implements PathTester {

    private final Board fBoard;
    private final PathTotalingAlgorithm fAlg;

    private enum Direction {
        N,
        NW,
        W,
        SW,
        S,
        SE,
        E,
        NE
    }

    public GamePathTester(final Board pBoard, final PathTotalingAlgorithm pAlg) {
        super();
        this.fBoard = pBoard;
        this.fAlg = pAlg;
    }

    @Override
    public boolean isValidPathFormat(final Path pPath) {
        if (pPath == null) return false;

        final ParsingPath p = pPath.getParsingPath();
        final int pathSize = p.size();

        if (pathSize <= 0) return false;

        // test if consecutive points are at most 1 space apart
        for (int i = 0; i < pathSize - 1; i++) {
            final Point A = p.get(i);
            final Point B = p.get(i + 1);
            final int deltaX = Math.abs(A.getX() - B.getX());
            final int deltaY = Math.abs(A.getY() - B.getY());

            if (deltaX > 1 || deltaY > 1) return false;
        }

        // test if there are no intersecting points
        for (int i = 0; i < pathSize - 1; i++) {
            for (int j = i + 1; j < pathSize; j++) {
                if (p.get(i).equals(p.get(j)))
                    return false;
            }
        }

        return true;
    }

    @Override
    public boolean pathHasTarget(final Path pPath, final int pTarget) {
        final ParsingPath path = pPath.getParsingPath();
        int total = 0;

        for (Point p = path.next(); path.hasNext(); p = path.next()) {
            total = this.fAlg.combine(total, this.fBoard.get(p));

            if (pTarget < total) return false;
        }

        return pTarget == total;
    }

    @Override
    public boolean solutionExists(final int pTarget) {
        // search each row, going down. Every solution going up will have already been found.
        final int size = this.fBoard.size();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final Set<Point> points = new HashSet<>(5);
                points.add(new GamePoint(col, row));
                final boolean result = this.search(
                        pTarget, this.fBoard.get(col, row),
                        row, col,
                        row, col,
                        points
                );

                if (result) return true;
            }
        }
        // thus no solution has been found
        return false;
    }

    // recursive search merely to see if a valid path exists
    private boolean search(final int pTarget, final int pTotal,
                           final int pRow, final int pCol,
                           final int pMinRow, final int pMinCol,
                           final Set<Point> pPoints) {
        for (final Direction dir : Direction.values()) {
            final int newRow = pRow + this.getRowOffset(dir);
            final int newCol = pCol + this.getColOffset(dir);

            if (this.isValidPoint(newRow, newCol, pMinRow, pMinCol)) {
                final Point newPoint = new GamePoint(newCol, newRow);
                if (!pPoints.contains(newPoint)) {
                    pPoints.add(newPoint);
                    final int newTotal = this.fAlg.combine(
                            pTotal, this.fBoard.get(newPoint)
                    );

                    if (newTotal == pTarget) return true;
                    else if (newTotal < pTarget) {
                        final boolean result = this.search(
                                pTarget, newTotal,
                                newRow, newCol,
                                pMinRow, pMinCol,
                                pPoints);
                        if (result) return true;
                    }
                    pPoints.remove(newPoint);
                }
            }
        }
        // finally, no path was found
        return false;
    }

    private int getRowOffset(final Direction pDir) {
        return switch (pDir) {
            case N, NW, NE -> -1;
            case S, SW, SE -> 1;
            default -> 0;
        };
    }

    private int getColOffset(final Direction pDir) {
        return switch (pDir) {
            case W, NW, SW -> -1;
            case E, NE, SE -> 1;
            default -> 0;
        };
    }

    private boolean isValidPoint(final int pRow, final int pCol,
                                 final int pMinRow, final int pMinCol) {
        final int boardSize = this.fBoard.size();
        // every path that traces to a prior column on the minimum row is
        // already traced,
        // eg: starting from (1,1) and ending on (0,1),
        // is already traced starting from (0,1) to (1,1)
        if (pRow == pMinRow && pCol < pMinCol) return false;
        else {
            return 0 <= pCol && pCol < boardSize
                    && pMinRow <= pRow && pRow < boardSize;
        }
    }

}
