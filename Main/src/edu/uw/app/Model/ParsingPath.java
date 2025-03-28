package edu.uw.app.Model;

import org.jetbrains.annotations.Nullable;

/**
 * Interface specifying an immutable path for parsing. A data class for paths.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
public interface ParsingPath {
    /**
     * Returns the size of the path in number of points.
     * @return the size of the path
     */
    int size();

    /**
     * Returns the point at the specified index. 0 indexed.
     * @param pIndex the point at the index
     * @return the point at the specified index.
     */
    Point get(int pIndex);

    /**
     * If there is another element to return.
     * @return if there is another element for the next method to return.
     */
    boolean hasNext();

    /**
     * Returns the next element in the path sequence.
     * @return the next element, throws
     */
    Point next();

    /**
     * Resets the parsing (next, hasNext) to start from the beginning.
     */
    void resetParse();
}
