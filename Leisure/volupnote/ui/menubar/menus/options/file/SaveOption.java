package volupnote.ui.menubar.menus.options.file;

import volupnote.ui.VolupFrame;
import volupnote.ui.menubar.menus.VMenuItem;
import volupnote.ui.tabs.VTab;
import volupnote.ui.tabs.VTabbedPane;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveOption extends VMenuItem {

    public SaveOption() {
        super(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK, "Save");
        addActionListener(event -> {
            VTabbedPane pane = VolupFrame.getTabContainer();
            VTab active = pane.getTabs().get(pane.getSelectedIndex());
            if (active != null) {
                try {
                    File file = new File(active.getPath());
                    FileWriter fileWriter = new FileWriter(file, false);
                    BufferedWriter writer = new BufferedWriter(fileWriter);
                    writer.write(active.getEditor().getText());
                    writer.close();
                    System.out.println("Saved " + active.getPath());
                } catch (IOException exception) {
                    System.err.println("Error saving file");
                    exception.printStackTrace();
                }
            }
        });
    }
}
