package edu.uw.app.View.MainMenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel that acts as the main menu.
 *
 * @author Roman Bureacov
 * @version 2025-03-31
 */
public class MainMenuPanel extends JPanel {

    /** Property name for when the game should commence. */
    public static final String PROPERTY_REQUEST_GAME = "I want to play the game!";

    private static final Dimension BUTTON_DIMENSION;
    private static final Dimension TITLE_DIMENSION;

    private final JLabel fTitle;
    private final JButton fStartGameButton;
    private final JButton fSettingsButton;

    static {
        BUTTON_DIMENSION = new Dimension(100, 25);
        TITLE_DIMENSION = new Dimension(150, 50);
    }

    /**
     * Creates a new main menu panel from which to configure and play the game.
     */
    public MainMenuPanel() {
        super();

        this.fTitle = new JLabel();
        this.fStartGameButton = new JButton();
        this.fSettingsButton = new JButton();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setup();
        this.setupActionListeners();
    }

    private void setup() {
        this.fTitle.setText("Number Snake!");
        this.fTitle.setSize(TITLE_DIMENSION);
        this.fTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.fStartGameButton.setText("Start Game");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.fStartGameButton.setSize(BUTTON_DIMENSION);
        this.fStartGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.fSettingsButton.setText("Settings");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.fSettingsButton.setSize(BUTTON_DIMENSION);
        this.fSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(this.fTitle);
        this.add(this.fStartGameButton);
        this.add(this.fSettingsButton);
    }

    private void setupActionListeners() {
        this.fStartGameButton.addActionListener(
                event -> this.firePropertyChange(
                        PROPERTY_REQUEST_GAME,
                        null,
                        null)
        );
    }

}
