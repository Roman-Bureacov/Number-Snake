package edu.uw.app.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to test for valid paths.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
class GamePathTester implements PathTester {

    private final Board iBoard;
    private final PathTotalingAlgorithm iAlg;

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
        this.iBoard = pBoard;
        this.iAlg = pAlg;
    }

    @Override
    public boolean isValidPathFormat(final Path pPath) {
        if (pPath == null) return false;

        final ParsingPath lParsingPath = pPath.getParsingPath();
        final int lPathSize = lParsingPath.size();

        if (lPathSize <= 0) return false;

        // test if consecutive points are at most 1 space apart
        for (int i = 0; i < lPathSize - 1; i++) {
            final Point lFirst = lParsingPath.get(i);
            final Point lSecond = lParsingPath.get(i + 1);
            final int lDeltaX = Math.abs(lFirst.getX() - lSecond.getX());
            final int lDeltaY = Math.abs(lFirst.getY() - lSecond.getY());

            if (lDeltaX > 1 || lDeltaY > 1) return false;
        }

        // test if there are no intersecting points
        for (int i = 0; i < lPathSize - 1; i++) {
            for (int j = i + 1; j < lPathSize; j++) {
                if (lParsingPath.get(i).equals(lParsingPath.get(j)))
                    return false;
            }
        }

        return true;
    }

    @Override
    public boolean pathHasTarget(final Path pPath, final int pTarget) {
        final ParsingPath lPath = pPath.getParsingPath();
        int lTotal = 0;

        for (Point p = lPath.next(); lPath.hasNext(); p = lPath.next()) {
            lTotal = this.iAlg.combine(lTotal, this.iBoard.get(p));

            if (pTarget < lTotal) return false;
        }

        return pTarget == lTotal;
    }

    @Override
    public boolean solutionExists(final int pTarget) {
        // search each row, going down. Every solution going up will have already been found.
        final int lSize = this.iBoard.size();
        for (int row = 0; row < lSize; row++) {
            for (int col = 0; col < lSize; col++) {
                final Set<Point> lPoints = new HashSet<>(5);
                lPoints.add(new GamePoint(col, row));
                final boolean lResult = this.search(
                        pTarget, this.iBoard.get(col, row),
                        row, col,
                        row, col,
                        lPoints
                );

                if (lResult) return true;
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
            final int lNewRow = pRow + this.getRowOffset(dir);
            final int lNewCol = pCol + this.getColOffset(dir);

            if (this.isValidPoint(lNewRow, lNewCol, pMinRow, pMinCol)) {
                final Point lNewPoint = new GamePoint(lNewCol, lNewRow);
                if (!pPoints.contains(lNewPoint)) {
                    pPoints.add(lNewPoint);
                    final int lNewTotal = this.iAlg.combine(
                            pTotal, this.iBoard.get(lNewPoint)
                    );

                    if (lNewTotal == pTarget) return true;
                    else if (lNewTotal < pTarget) {
                        final boolean lResult = this.search(
                                pTarget, lNewTotal,
                                lNewRow, lNewCol,
                                pMinRow, pMinCol,
                                pPoints);
                        if (lResult) return true;
                    }
                    pPoints.remove(lNewPoint);
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
        final int lBoardSize = this.iBoard.size();
        // every path that traces to a prior column on the minimum row is
        // already traced,
        // eg: starting from (1,1) and ending on (0,1),
        // is already traced starting from (0,1) to (1,1)
        if (pRow == pMinRow && pCol < pMinCol) return false;
        else {
            return 0 <= pCol && pCol < lBoardSize
                    && pMinRow <= pRow && pRow < lBoardSize;
        }
    }

}
