package volupnote.ui.menubar.menus;

import volupnote.ui.menubar.menus.options.preferences.FontOption;

import java.awt.event.KeyEvent;

public class PreferencesMenu extends VMenu {

    public PreferencesMenu() {
        super("Preferences", KeyEvent.VK_P);
        add(new FontOption());
    }
}