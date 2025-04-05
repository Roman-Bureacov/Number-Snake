package edu.uw.app.View.MainMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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

    private static final Font TITLE_FONT;
    private static final Font BUTTON_FONT;

    static {
        final int lFontSize = 50;
        BUTTON_DIMENSION = new Dimension(100, 100);
        TITLE_DIMENSION = new Dimension(500, lFontSize * 2 + 50 * 2);

        TITLE_FONT = new Font("Serif", Font.BOLD, lFontSize);
        BUTTON_FONT = new Font("Sans-Serif", Font.PLAIN, 12);
    }

    /**
     * Creates a new main menu panel from which to configure and play the game.
     */
    public MainMenuPanel() {
        super();

        this.fTitle = new JLabel();
        this.fStartGameButton = new JButton();
        this.fSettingsButton = new JButton();

        this.setup();
        this.setupActionListeners();
    }

    private void setup() {
        this.setLayout(new BorderLayout());

        this.fTitle.setText("Number Snake!");
        this.fTitle.setFont(TITLE_FONT);
        this.fTitle.setPreferredSize(TITLE_DIMENSION);
        this.fTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.fTitle.setVerticalAlignment(SwingConstants.CENTER);

        this.add(this.fTitle, BorderLayout.NORTH);

        final JPanel lButtonPanel = new JPanel();
        lButtonPanel.setLayout(new BoxLayout(lButtonPanel, BoxLayout.Y_AXIS));

        this.fStartGameButton.setText("Start Game");
        this.fStartGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.fStartGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.fStartGameButton.setFont(BUTTON_FONT);
        lButtonPanel.add(this.fStartGameButton);

        this.fSettingsButton.setText("Settings");
        this.fSettingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.fSettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.fSettingsButton.setFont(BUTTON_FONT);
        lButtonPanel.add(this.fSettingsButton);

        this.add(lButtonPanel, BorderLayout.SOUTH);
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
