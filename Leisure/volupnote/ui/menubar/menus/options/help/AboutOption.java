package volupnote.ui.menubar.menus.options.help;

import volupnote.ui.about.AboutFrame;
import volupnote.ui.menubar.menus.VMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AboutOption extends VMenuItem {

    public AboutOption() {
        super(KeyEvent.VK_UNDEFINED, KeyEvent.VK_UNDEFINED, "About");
        addActionListener(event -> new AboutFrame().setVisible(true));
    }
}