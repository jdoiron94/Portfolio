package volupnote.ui.menubar.menus.options.file;

import volupnote.ui.VolupFrame;
import volupnote.ui.menubar.menus.VMenuItem;
import volupnote.ui.tabs.VTab;
import volupnote.ui.tabs.VTabbedPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAsOption extends VMenuItem {

    public SaveAsOption() {
        super(KeyEvent.VK_A, KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, "Save As");
        addActionListener(event -> {
            VTabbedPane pane = VolupFrame.getTabContainer();
            VTab active = pane.getTabs().get(pane.getSelectedIndex());
            if (active != null) {
                JFileChooser chooser = new JFileChooser();
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setFileFilter(new FileNameExtensionFilter("Java files", "java"));
                chooser.setCurrentDirectory(new File(active.getPath()).getParentFile());
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File updated = chooser.getSelectedFile().getName().contains(".java") ? chooser.getSelectedFile() : new File(chooser.getSelectedFile() + ".java");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(updated, false));
                        writer.write(active.getEditor().getText());
                        writer.close();
                        System.out.println("Saved (as) " + updated);
                    } catch (IOException exception) {
                        System.err.println("Error saving file");
                        exception.printStackTrace();
                    }
                }
            }
        });
    }
}