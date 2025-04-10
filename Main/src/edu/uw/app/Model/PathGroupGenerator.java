package edu.uw.app.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Static Class that generates grouping of points that can describe a potential path.
 *
 * @author Roman Bureacov
 * @version 2025-04-07
 */
final class PathGroupGenerator {

    private static final int SIZE = 4;
    private static long sTests = 0L;

    /**
     * The eight possible directions to go in
     */
    private enum Direction {
        N, NW, W,
        SW, S, SE,
        E, NE
    }

    public static void main(final String... pArgs) {

        final long lStartTime = System.currentTimeMillis();
        final Set<Set<Point>> lPaths = getAllPaths(5);
        final long lEndTime = System.currentTimeMillis();
        final long lTotalMillis = lEndTime - lStartTime;

        final long lTotalSeconds = (lTotalMillis / 1000L) % 60L;
        final long lTotalMinutes = lTotalMillis / 1000L / 60L;
        final long lModdedMillis = lTotalMillis % 1000L;

        System.out.format("Paths set size: %d\n", lPaths.size());
        System.out.format("Number of tests done: %d\n", sTests);
        System.out.format(
                "Total time: %02d:%02d.%02d minutes\n",
                lTotalMinutes, lTotalSeconds, lModdedMillis
        );
        /*
        lPaths.forEach(
                set -> {
                    set.forEach(point -> System.out.format("(%d, %d) ", point.getX(), point.getY()));
                    System.out.println();
                });
        */
    }

    private PathGroupGenerator() {
        super();
    }

    // recursive method to find all paths
    private static void addPoint(final int pGridSize,
                                 final int pMinRow, final int pMinCol,
                                 final int pRow, final int pCol,
                                 final Set<Point> pPathPoints, final Set<Set<Point>> pPaths) {

        for (final Direction dir : Direction.values()) {
            final int lNewRow = pRow + getRowOffset(dir);
            final int lNewCol = pCol + getColOffset(dir);
            final Point lNewPoint = new GamePoint(lNewCol, lNewRow);

            sTests++;

            if (isValid(pGridSize, pMinRow, pMinCol, lNewRow, lNewCol)
                    && !pPathPoints.contains(lNewPoint)) {
                pPathPoints.add(lNewPoint);
                pPaths.add(Set.copyOf(pPathPoints));
                addPoint(pGridSize, pMinRow, pMinCol, lNewRow, lNewCol, pPathPoints, pPaths);
                pPathPoints.remove(lNewPoint);
            }
        }
    }

    // paths should not cross above and to the left of the starting point
    // those points should already have existing paths
    private static boolean isValid(final int pGridSize,
                                   final int pMinRow, final int pMinCol,
                                   final int pRow, final int pCol) {
        if (0 <= pRow && pRow <= pMinRow) {
            return pMinCol < pCol && pCol < pGridSize;
        } else {
            return 0 <= pRow && pRow < pGridSize
                    && 0 <= pCol && pCol < pGridSize;
        }
    }

    private static int getColOffset(final Direction pDir) {
        return switch(pDir) {
            case NE, E, SE -> 1;
            case NW, W, SW -> -1;
            default -> 0;
        };
    }

    private static int getRowOffset(final Direction pDir) {
        return switch(pDir) {
            case SE, S, SW -> 1;
            case NE, N, NW -> -1;
            default -> 0;
        };
    }

    public static Set<Set<Point>> getAllPaths(final int pGridSize) {
        final Set<Set<Point>> lPaths = new HashSet<>(100);

        // only cover the first quadrant, the rest of the paths can
        // be made by transposing the existing paths along the mirror
        // x and y axes
        final int lMaxDistance = (int) Math.ceil(((double) pGridSize / 2d));
        for (int row = 0; row < lMaxDistance; row++) {
            for (int col = 0; col < lMaxDistance; col++) {
                final Set<Point> lPathPoints = new HashSet<>(4);
                addPoint(pGridSize,
                        row, col,
                        row, col,
                        lPathPoints, lPaths);
            }
        }

        return lPaths;
    }
}
