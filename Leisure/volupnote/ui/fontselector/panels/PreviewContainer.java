package volupnote.ui.fontselector.panels;

import volupnote.ui.fontselector.FontVars;
import volupnote.ui.view.CenteredEditorKit;

import javax.swing.*;
import java.awt.*;

public class PreviewContainer extends JPanel {

    private static final JTextPane preview = new JTextPane();

    public PreviewContainer() {
        setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(420, 100));
        setBorder(BorderFactory.createTitledBorder("Preview:"));
        preview.setEditorKit(new CenteredEditorKit());
        preview.setFont(FontVars.getCurrentFont());
        preview.setText("aAbBcCdDeEfF");
        preview.setEditable(false);
        JScrollPane pane = new JScrollPane(preview);
        pane.setPreferredSize(new Dimension(420, 100));
        add(pane, BorderLayout.CENTER);
    }

    protected static Font getCurrentFont() {
        return preview.getFont();
    }

    protected static void setDesiredFontStyle(int style) {
        preview.setFont(preview.getFont().deriveFont(style));
    }

    protected static void setDesiredFontSize(float size) {
        preview.setFont(preview.getFont().deriveFont(size));
    }

    protected static void setDesiredFontName(String name) {
        preview.setFont(new Font(name, preview.getFont().getStyle(), preview.getFont().getSize()));
    }
}