package volupnote.ui.fontselector;

import volupnote.Context;
import volupnote.ui.fontselector.panels.ButtonContainer;
import volupnote.ui.fontselector.panels.FontContainer;
import volupnote.ui.fontselector.panels.PreviewContainer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Font;

public class FontSelector extends JFrame {

    private final FontContainer fontContainer = new FontContainer();

    public FontSelector() {
        setTitle("Select Font");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        setIconImage(Context.FACTORY.loadIcon("Font Selector").getImage());
        add(fontContainer, BorderLayout.NORTH);
        add(new PreviewContainer(), BorderLayout.CENTER);
        add(new ButtonContainer(this), BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    public Font getSelectedFont() {
        return fontContainer.getDesiredFont();
    }
}