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
        super("Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                final VTabbedPane pane = VolupFrame.getTabContainer();
                final VTab active = pane.getTabs().get(pane.getSelectedIndex());
                if (active != null) {
                    try {
                        final BufferedWriter writer = new BufferedWriter(new FileWriter(new File(active.getPath()), false));
                        writer.write(active.getEditor().getText());
                        writer.close();
                        System.out.println("Saved " + active.getPath());
                    } catch (final IOException ignored) {
                        System.err.println("Error saving file");
                        ignored.printStackTrace();
                    }
                }
            }
        });
    }
}
