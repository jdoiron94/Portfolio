package volupnote.ui.menubar.menus;

import volupnote.ui.menubar.menus.options.file.OpenOption;
import volupnote.ui.menubar.menus.options.file.SaveAsOption;
import volupnote.ui.menubar.menus.options.file.SaveOption;

import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

public class FileMenu extends VMenu {

    public FileMenu() {
        super("File", KeyEvent.VK_F);
        SaveOption save = new SaveOption();
        SaveAsOption saveAs = new SaveAsOption();
        OpenOption open = new OpenOption();
        add(save);
        add(saveAs);
        add(open);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        add(exit);
    }
}