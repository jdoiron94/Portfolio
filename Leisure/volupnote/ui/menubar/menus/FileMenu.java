package volupnote.ui.menubar.menus;

import volupnote.ui.menubar.menus.options.file.OpenOption;
import volupnote.ui.menubar.menus.options.file.SaveAsOption;
import volupnote.ui.menubar.menus.options.file.SaveOption;

import java.awt.event.KeyEvent;

public class FileMenu extends VMenu {

    public FileMenu() {
        super("File", KeyEvent.VK_F);
        add(new SaveOption());
        add(new SaveAsOption());
        add(new OpenOption());
    }
}