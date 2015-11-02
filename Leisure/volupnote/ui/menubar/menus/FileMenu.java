package volupnote.ui.menubar.menus;

import volupnote.ui.menubar.menus.options.file.OpenOption;
import volupnote.ui.menubar.menus.options.file.SaveAsOption;
import volupnote.ui.menubar.menus.options.file.SaveOption;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileMenu extends VMenu {

    public FileMenu() {
        super("File", KeyEvent.VK_F);
        add(new SaveOption());
        add(new SaveAsOption());
        add(new OpenOption());
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e-> System.exit(0));
        add(exit);
    }
}