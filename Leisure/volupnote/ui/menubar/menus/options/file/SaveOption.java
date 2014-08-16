package volupnote.ui.menubar.menus.options.file;

import volupnote.ui.VolupFrame;
import volupnote.ui.menubar.menus.VMenuItem;
import volupnote.ui.tabs.VTab;
import volupnote.ui.tabs.VTabbedPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveOption extends VMenuItem {

    public SaveOption() {
        super(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK, "Save");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                VTabbedPane pane = VolupFrame.getTabContainer();
                VTab active = pane.getTabs().get(pane.getSelectedIndex());
                if (active != null) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(active.getPath()), false));
                        writer.write(active.getEditor().getText());
                        writer.close();
                        System.out.println("Saved " + active.getPath());
                    } catch (IOException exception) {
                        System.err.println("Error saving file");
                        exception.printStackTrace();
                    }
                }
            }
        });
    }
}
