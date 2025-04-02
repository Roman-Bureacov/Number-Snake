package edu.uw.app.View;

import static edu.uw.app.View.Game.GamePanel.PROPERTY_REQUEST_MAIN_MENU;
import static edu.uw.app.View.MainMenu.MainMenuPanel.PROPERTY_REQUEST_GAME;
import edu.uw.app.View.Game.GamePanel;
import edu.uw.app.View.MainMenu.MainMenuPanel;
import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

class MainPanel extends JPanel implements PropertyChangeListener {
    /*
    TODO:
     here's the potential idea: interchange what panel goes in the content pane
     that way you can interchange between the main menu and the actual game itself
     */

    private final CardLayout fLayout = new CardLayout();
    private final JPanel fMainMenuPanel;
    private JPanel fGamePanel;

    private final String fGamePanelName = "I'm the game";
    private final String fMainMenuPanelName = "I'm the main menu";

    public MainPanel() {
        super();
        this.fMainMenuPanel = new MainMenuPanel();
        this.fGamePanel = new GamePanel();

        this.setup();
    }

    private void setPropertyChangeListeners() {
        this.fMainMenuPanel.addPropertyChangeListener(PROPERTY_REQUEST_GAME, this);
        this.fGamePanel.addPropertyChangeListener(PROPERTY_REQUEST_MAIN_MENU, this);
    }

    private void setup() {
        this.setLayout(this.fLayout);
        this.add(this.fGamePanel);
        this.add(this.fMainMenuPanel);

        this.setPropertyChangeListeners();
        this.fLayout.addLayoutComponent(this.fMainMenuPanel, this.fMainMenuPanelName);
        this.fLayout.addLayoutComponent(this.fGamePanel, this.fGamePanelName);
        this.fLayout.show(this, this.fMainMenuPanelName);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pEvent) {
        switch(pEvent.getPropertyName()) {
            case PROPERTY_REQUEST_GAME -> this.fLayout.show(this, this.fGamePanelName);
            case PROPERTY_REQUEST_MAIN_MENU -> this.fLayout.show(this, this.fMainMenuPanelName);
            default -> { }
        }
    }
}
