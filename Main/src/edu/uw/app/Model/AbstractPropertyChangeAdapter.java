package edu.uw.app.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Abstract class that handles property change events for an object.
 */
public abstract class AbstractPropertyChangeAdapter {
    protected final PropertyChangeSupport fPropChSupp = new PropertyChangeSupport(this);

    /**
     * Appends a property change listener to the game.
     * @param pListener the listener to append
     */
    public void addPropertyChangeListener(final PropertyChangeListener pListener) {
        this.fPropChSupp.addPropertyChangeListener(pListener);
    }

    /**
     * Removes the specified property change listener.
     * @param pListener the listener to remove
     */
    void removePropertyChangeListener(final PropertyChangeListener pListener) {
        this.fPropChSupp.removePropertyChangeListener(pListener);
    }
}
