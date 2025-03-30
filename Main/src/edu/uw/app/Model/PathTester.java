package edu.uw.app.Model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Class to test for valid paths.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
final class PathTester {

    private static final Random RANDOMIZER = new Random();
    private static final int MAX_RANDOM_VALUE = 10;

    private static enum Direction {
        N,
        NW,
        W,
        SW,
        S,
        SE,
        E,
        NE
    }

    private PathTester() {
        super();
    }

    /**
     * Test to see if the pathing is valid.
     */
    public static boolean isValidPath(final Path pPath) {
        final ParsingPath p = pPath.getParsingPath();
        final int pathSize = p.size();

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

    public static boolean pathHasTarget(final Board pBoard, final Path pPath,
                                        final int pTarget, final PathTotalingAlgorithm pAlg) {
        final ParsingPath path = pPath.getParsingPath();
        int total = 0;

        for (Point p = path.next(); path.hasNext(); p = path.next()) {
            total = pAlg.combine(total, pBoard.get(p));

            if (pTarget < total) return false;
        }

        return pTarget == total;
    }

    /**
     * Tests if a given board contains a solution for the target.
     * @param pBoard the board to look for the solution within
     * @param pTarget the target to look for
     */
    public static boolean solutionExists(final Board pBoard,
                                         final int pTarget, final PathTotalingAlgorithm pAlg) {
        // search each row, going down. Every solution going up will have already been found.
        final int size = pBoard.size();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final Set<Point> points = new HashSet<>(5);
                points.add(new GamePoint(col, row));
                final boolean result = search(
                        pBoard, pTarget,
                        pBoard.get(col, row), pAlg,
                        row, col, row,
                        points);

                if (result) return true;
            }
        }
        // thus no solution has been found
        return false;
    }

    // recursive search merely to see if a valid path exists
    private static boolean search(final Board pBoard, final int pTarget,
                                  final int pTotal, final PathTotalingAlgorithm pAlg,
                                  final int pRow, final int pCol, final int pMinRow,
                                  final Set<Point> pPoints) {
        for (final Direction dir : Direction.values()) {
            final int newRow = pRow + getRowOffset(dir);
            final int newCol = pCol + getColOffset(dir);

            if (isValidPoint(newRow, newCol, pMinRow, pBoard.size())) {
                final Point newPoint = new GamePoint(newCol, newRow);
                if (!pPoints.contains(newPoint)) {
                    pPoints.add(newPoint);
                    final int newTotal = pAlg.combine(
                            pTotal, pBoard.get(newPoint)
                    );

                    if (newTotal == pTarget) return true;
                    else if (newTotal < pTarget) {
                        final boolean result = search(pBoard, pTarget,
                                newTotal, pAlg,
                                newRow, newCol, pMinRow,
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

    private static int getRowOffset(final Direction pDir) {
        return switch (pDir) {
            case N, NW, NE -> -1;
            case S, SW, SE -> 1;
            default -> 0;
        };
    }

    private static int getColOffset(final Direction pDir) {
        return switch (pDir) {
            case W, NW, SW -> -1;
            case E, NE, SE -> 1;
            default -> 0;
        };
    }

    private static boolean isValidPoint(final int pRow, final int pCol,
                                 final int pMinRow, final int pBoardSize) {
        return 0 <= pCol && pCol < pBoardSize
                && pMinRow <= pRow && pRow < pBoardSize;
    }

    /**
     * Test the given path with the current board.
     * @return if the path is valid.
     */
    public static boolean testPath(final Board pBoard, final Path pPath,
                                   final int pTarget, final PathTotalingAlgorithm pAlg) {
        final boolean validPath = isValidPath(pPath) && pathHasTarget(pBoard, pPath, pTarget, pAlg);
        if (validPath) {
            final ParsingPath path = pPath.getParsingPath();
            for (Point p = path.next(); path.hasNext(); p = path.next()) {
                pBoard.set(p, newRandomInt());
            }

            solutionExists(pBoard, pTarget, pAlg);
            // TODO: maybe this could be modified to replace values along the path until solution exists?
        }
        return validPath;
    }

    private static int newRandomInt() {
        // TODO: maybe this could be mutated, the maximum random value?
        return RANDOMIZER.nextInt(MAX_RANDOM_VALUE) + 1;
    }

    /**
     * resets the board with new values from 1-9, inclusive.
     */
    public static void resetBoard(final Board pBoard) {
        final int boardSize = pBoard.size();
        for (int x = 0; x < pBoard.size(); x++) {
            for (int y = 0; y < pBoard.size(); y++) {
                pBoard.set(x, y, newRandomInt());
            }
        }
    }
}
