package volupnote.ui.menubar.menus;

import volupnote.ui.menubar.menus.options.help.AboutOption;

import java.awt.event.KeyEvent;

public class HelpMenu extends VMenu {

    public HelpMenu() {
        super("Help", KeyEvent.VK_H);
        AboutOption about = new AboutOption();
        add(about);
    }
}
