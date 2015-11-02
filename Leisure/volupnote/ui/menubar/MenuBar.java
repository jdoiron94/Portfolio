package volupnote.ui.menubar;

import volupnote.ui.menubar.menus.FileMenu;
import volupnote.ui.menubar.menus.HelpMenu;
import volupnote.ui.menubar.menus.PreferencesMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        add(new FileMenu());
        add(new PreferencesMenu());
        add(new HelpMenu());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                requestFocus(true);
            }
        });
        //setHelpMenu(new Help());
    }
}