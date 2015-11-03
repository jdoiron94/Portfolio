package volupnote.ui.fontselector.panels;

import volupnote.ui.fontselector.FontVars;
import volupnote.ui.view.CenteredEditorKit;
import volupnote.ui.view.CenteredView;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class PreviewContainer extends JPanel {

    private static final JTextPane preview = new JTextPane();

    public PreviewContainer() {
        BorderLayout layout = new BorderLayout(0, 0);
        setLayout(layout);
        Dimension dimension = new Dimension(420, 100);
        setPreferredSize(dimension);
        setBorder(BorderFactory.createTitledBorder("Preview:"));
        CenteredEditorKit kit = new CenteredEditorKit();
        preview.setEditorKit(kit);
        preview.setFont(FontVars.getCurrentFont());
        preview.setText("aAbBcCdDeEfF");
        preview.setEditable(false);
        JScrollPane pane = new JScrollPane(preview);
        Dimension paneDims = new Dimension(420, 100);
        pane.setPreferredSize(paneDims);
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