package volupnote.ui.fontselector;

import volupnote.Context;
import volupnote.ui.fontselector.panels.ButtonContainer;
import volupnote.ui.fontselector.panels.FontContainer;
import volupnote.ui.fontselector.panels.PreviewContainer;

import javax.swing.*;
import java.awt.*;

public class FontSelector extends JDialog {

    private final FontContainer fontContainer = new FontContainer();

    public FontSelector() {
        setTitle("Select Font");
        System.out.println("loaded font container");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        setIconImage(Context.factory.loadIcon("Font Selector").getImage());
        add(fontContainer, BorderLayout.NORTH);
        add(new PreviewContainer(), BorderLayout.CENTER);
        add(new ButtonContainer(this), BorderLayout.SOUTH);
        pack();
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    public Font getSelectedFont() {
        return fontContainer.getDesiredFont();
    }
}