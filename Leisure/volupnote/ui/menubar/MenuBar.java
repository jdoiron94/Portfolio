package volupnote.ui.menubar;

import volupnote.ui.menubar.menus.FileMenu;
import volupnote.ui.menubar.menus.HelpMenu;
import volupnote.ui.menubar.menus.PreferencesMenu;

import javax.swing.JMenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        FileMenu file = new FileMenu();
        PreferencesMenu preference = new PreferencesMenu();
        HelpMenu help = new HelpMenu();
        add(file);
        add(preference);
        add(help);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                requestFocus(true);
            }
        });
        //setHelpMenu(new Help());
    }
}