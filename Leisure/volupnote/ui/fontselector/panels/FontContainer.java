package volupnote.ui.fontselector.panels;

import javax.swing.*;
import java.awt.*;

public class FontContainer extends JPanel {

    private final FontFamilyContainer fontFamily = new FontFamilyContainer();
    private final FontStyleContainer fontStyle = new FontStyleContainer();
    private final FontSizeContainer fontSize = new FontSizeContainer();

    public FontContainer() {
        setLayout(new BorderLayout(0, 0));
        add(fontFamily, BorderLayout.WEST);
        add(fontStyle, BorderLayout.CENTER);
        add(fontSize, BorderLayout.EAST);
    }

    @SuppressWarnings("MagicConstant")
    public Font getDesiredFont() {
        return new Font(fontFamily.getFontName(), fontStyle.getFontStyle(), fontSize.getFontSize());
    }
}