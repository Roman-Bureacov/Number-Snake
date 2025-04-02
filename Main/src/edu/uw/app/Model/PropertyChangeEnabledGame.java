package edu.uw.app.Model;

/**
 * A game that has the ability to append property change listeners.
 * @author Roman Bureacov
 * @version 2025-04-02
 */
public abstract class PropertyChangeEnabledGame
        extends AbstractPropertyChangeAdapter
        implements Game {

    /** Property name for if the path successfully resolved. */
    public static final String PROPERTY_GOOD_PATH = "path resolved";
    /** Property name for if the path failed to resolve. */
    public static final String PROPERTY_BAD_PATH = "path did not resolve";
    /** Property name for when a new game is created */
    public static final String PROPERTY_NEW_GAME = "started new game";

}
