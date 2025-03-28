package edu.uw.app.Model;

/**
 * Interface specifying an immutable path for parsing. A data class for paths.
 */
interface ParsingPath {
    /**
     * Returns the size of the path in number of points.
     */
    int size();

    /**
     * Returns the point at the specified index. 0 indexed.
     */
    Point get(int pIndex);

    /**
     * If there is another element to return.
     */
    boolean hasNext();

    /**
     * Returns the next element in the path sequence.
     */
    Point next();

    /**
     * Resets the parsing (next, hasNext) to start from the beginning.
     */
    void resetParse();
}
