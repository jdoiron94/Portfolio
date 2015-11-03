package volupnote.ui.fontselector;

import volupnote.Context;
import volupnote.ui.fontselector.panels.ButtonContainer;
import volupnote.ui.fontselector.panels.FontContainer;
import volupnote.ui.fontselector.panels.PreviewContainer;

import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class FontSelector extends JDialog {

    private final FontContainer fontContainer = new FontContainer();

    public FontSelector() {
        setTitle("Select Font");
        System.out.println("loaded font container");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        BorderLayout layout = new BorderLayout(0, 0);
        setLayout(layout);
        setIconImage(Context.factory.loadIcon("Font Selector").getImage());
        PreviewContainer preview = new PreviewContainer();
        ButtonContainer button = new ButtonContainer(this);
        add(fontContainer, BorderLayout.NORTH);
        add(preview, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        pack();
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    public Font getSelectedFont() {
        return fontContainer.getDesiredFont();
    }
}