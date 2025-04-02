package edu.uw.app.View.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Utility class for common manipulations on Swing's Graphics object.
 *
 * @author Roman Bureacov
 * @version 2025-04-02
 */
public final class GraphicsHandler {
    private GraphicsHandler() {
        super();
    }

    /**
     * Returns the Graphics with antialiasing enabled.
     * @param pGraphics the graphics object to manipulate
     * @return the graphics object with antialiasing enabled and cast to Graphics 2D
     */
    public static Graphics2D enableAntiAliasing(final Graphics pGraphics) {
        final Graphics2D lGraphics = (Graphics2D) pGraphics;
        lGraphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        return lGraphics;
    }
}
