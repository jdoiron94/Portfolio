package volupnote.ui.menubar.menus.options.preferences;

import volupnote.ui.fontselector.FontSelector;

import javax.swing.JMenuItem;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FontOption extends JMenuItem {

    public FontOption() {
        super("Font", KeyEvent.VK_F);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new FontSelector().setVisible(true);
                    }
                });
            }
        });
    }
}