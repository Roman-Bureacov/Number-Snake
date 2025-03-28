package edu.uw.app.Model;

/**
 * Representation for a path on the numeric game board.
 * The purpose is to be able to construct paths and create new ones when necessary.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
interface Path {
    /**
     * Adds a point to the path.
     */
    void add(Point pPoint);

    /**
     * Adds a point to the path.
     */
    void add(int pX, int pY);

    /**
     * Returns an immutable path for parsing.
     */
    ParsingPath getParsingPath();
}
