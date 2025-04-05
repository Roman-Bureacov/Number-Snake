package edu.uw.app.View.Game;

import static edu.uw.app.Model.PropertyChangeEnabledGame.PROPERTY_GOOD_PATH;
import edu.uw.app.Model.PropertyChangeEnabledGame;
import edu.uw.app.View.util.GraphicsHandler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 * Class that draws the game board as a panel.
 * @author Roman Bureacov
 * @version 2025-04-02
 */
class GameBoardPanel extends JPanel implements PropertyChangeListener {

    private static final int PANEL_SIZE = 500;

    private final PropertyChangeEnabledGame fGame;
    private int[][] fBoard;

    public GameBoardPanel(final PropertyChangeEnabledGame pGame) {
        super();
        this.fGame = pGame;
        pGame.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pEvent) {
        if (PROPERTY_GOOD_PATH.equals(pEvent.getPropertyName())) {
            this.fBoard = (int[][]) pEvent.getNewValue();
            this.repaint();
        }
    }

    @Override
    protected void paintComponent(final Graphics pGraphics) {
        super.paintComponent(pGraphics);
        final Graphics2D lGraphics = GraphicsHandler.enableAntiAliasingAndReturn(pGraphics);
        this.drawBoard(this.fBoard, lGraphics);
    }

    private void drawBoard(final int[][] pBoard, final Graphics2D pGraphics) {
        final int lBoardSize = pBoard.length;
        for (int lRow = 0; lRow < lBoardSize; lRow++) {
            for (int lCol = 0; lCol < lBoardSize; lCol++) {
                this.drawUnit(lRow, lCol, pGraphics);
            }
        }
    }

    private void drawUnit(final int pRow, final int pCol, final Graphics2D pGraphics) {
        final int lUnitSize = PANEL_SIZE / this.fBoard.length;
        final int lUnitX = lUnitSize * pCol;
        final int lUnitY = lUnitSize * pRow;
        final int lInset = 3;

        pGraphics.drawOval(lUnitX, lUnitY, lUnitSize, lUnitSize);
        pGraphics.drawOval(lUnitX, lUnitY, lUnitSize - lInset, lUnitSize - lInset);
        // TODO: pickup from here
    }
}
