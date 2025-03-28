package edu.uw.app.Model;

/**
 * Class to test for valid paths.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
class GamePathTester implements PathTester {
    private final PathTotalingAlgorithm fPathingAlgorithm;

    public GamePathTester(final PathTotalingAlgorithm pAlgorithm) {
        super();
        this.fPathingAlgorithm = pAlgorithm;
    }

    @Override
    public boolean isValid(final Path pPath) {
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
        for (int i = 0; i < pathSize; i++) {
            for (int j = i; j < pathSize; j++) {
                if (p.get(i).getX() == p.get(j).getX()
                    && p.get(i).getY() == p.get(j).getY())
                    return false;
            }
        }

        return true;
    }

    @Override
    public boolean hasTarget(final Board pBoard, final Path pPath, final int pTarget) {
        final ParsingPath path = pPath.getParsingPath();
        int total = 0;

        for (Point p = path.next(); path.hasNext(); p = path.next()) {
            total = this.fPathingAlgorithm.aggregate(total, pBoard.get(p));

            if (pTarget < total) return false;
        }

        return pTarget == total;
    }
}
