package edu.uw.app.View;

import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import javax.swing.SwingUtilities;

public final class Application {
    public static void main(final String... pArgs) {
        FlatMonokaiProIJTheme.setup();
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
