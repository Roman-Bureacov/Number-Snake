package edu.uw.app.Model;

import org.jetbrains.annotations.Nullable;

/**
 * Representation for a path on the numeric game board.
 * The purpose is to be able to construct paths and create new ones when necessary.
 */
interface Path {
    /**
     * Checks if the path is valid.
     * <br>
     * A path is valid if:
     * <ul>
     *     <li>It does not contain intersecting points</li>
     *     <li>It contains points only connected to nearby points, such that the
     *     distance between two consecutive points in the path is no greater
     *     than 1 in both the x and y directions.</li>
     * </ul>
     */
    boolean isValid();

    /**
     * Adds a point to the path.
     */
    void add(Point pPoint);

    /**
     * Gets the next available point. Returns null if no more points are available.
     * @return the next point available point, otherwise null if there are no more points.
     */
    @Nullable
    Point getNextPoint();
}
