package edu.uw.app.View.Game;

import edu.uw.app.Model.NumberSnakeGame;
import edu.uw.app.Model.PropertyChangeEnabledGame;
import javax.swing.JPanel;

/**
 * Class that holds the game and the visual display for the game as a JPanel.
 *
 * @author Roman Bureacov
 * @version 2025-03-31
 */
public class GamePanel extends JPanel {

    /** property name for requesting to go back to the main menu. */
    public static final String PROPERTY_REQUEST_MAIN_MENU = "I want to go back!";

    private final PropertyChangeEnabledGame fGame = new NumberSnakeGame();
    private final JPanel fGameBoardPanel = new JPanel(); // TODO

    public GamePanel() {
        super();

    }
}
