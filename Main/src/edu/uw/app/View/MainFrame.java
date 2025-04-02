package edu.uw.app.View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

class MainFrame extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public MainFrame() {
        super();

        this.setup();

        this.add(new MainPanel());

        this.setVisible(true);
    }

    private void setup() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Number Snake!");

        final Dimension lScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int lCenterX = (int) (lScreenSize.getWidth() - (double) WIDTH) / 2;
        final int lCenterY = (int) (lScreenSize.getHeight() - (double) HEIGHT) / 2;
        this.setLocation(lCenterX, lCenterY);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
