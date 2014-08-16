package volupnote.ui.menubar.menus.options.file;

import volupnote.io.IOUtils;
import volupnote.ui.VolupFrame;
import volupnote.ui.menubar.menus.VMenuItem;
import volupnote.ui.tabs.VTab;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenOption extends VMenuItem {

    public OpenOption() {
        super(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK, "Open...");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(false);
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setFileFilter(new FileNameExtensionFilter("Java files", "java"));
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selected = chooser.getSelectedFile();
                    if (selected != null) {
                        VTab tab = new VTab(selected.getName(), selected.getPath(), null);
                        tab.getEditor().setText(IOUtils.read(selected));
                        VolupFrame.addTab(tab);
                    }
                }
            }
        });
    }
}