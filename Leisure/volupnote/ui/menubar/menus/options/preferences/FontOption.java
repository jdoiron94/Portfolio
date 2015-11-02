package volupnote.ui.menubar.menus.options.preferences;

import volupnote.ui.fontselector.FontSelector;

import javax.swing.JMenuItem;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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