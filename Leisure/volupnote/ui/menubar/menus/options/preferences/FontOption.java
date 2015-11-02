package volupnote.ui.menubar.menus.options.preferences;

import volupnote.ui.fontselector.FontSelector;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FontOption extends JMenuItem {

    public FontOption() {
        super("Font", KeyEvent.VK_F);
        addActionListener(event -> {
            System.out.println("font clicked");
            FontSelector selector = new FontSelector();
            selector.setVisible(true);
        });
    }
}