package edu.uw.app.Model;

/**
 * Representation for a path on the numeric game board.
 * The purpose is to be able to construct paths and create new ones when necessary.
 */
interface Path {
    /**
     * Adds a point to the path.
     */
    void add(Point pPoint);

    /**
     * Returns an immutable path for parsing.
     */
    ParsingPath getParsingPath();
}
