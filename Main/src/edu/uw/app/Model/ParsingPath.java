package edu.uw.app.Model;

import java.util.Iterator;

/**
 * Interface specifying an immutable path for parsing. A data class for paths.
 */
interface ParsingPath extends Iterator<Point> {
    /**
     * Returns the size of the path in number of points.
     */
    int size();

    /**
     * Returns the point at the specified index.
     */
    Point get(int pIndex);
}
