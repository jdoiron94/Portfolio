package volupnote.ui.menubar.menus;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class VMenuItem extends JMenuItem {

    public VMenuItem(int mnemonic, int key, int modifier, String name) {
        super(mnemonic != KeyEvent.VK_UNDEFINED && key != KeyEvent.VK_UNDEFINED && modifier != KeyEvent.VK_UNDEFINED ? name + "      " : name);
        if (mnemonic != KeyEvent.VK_UNDEFINED) {
            setMnemonic(mnemonic);
        }
        if (key != KeyEvent.VK_UNDEFINED && modifier != KeyEvent.VK_UNDEFINED) {
            setAccelerator(KeyStroke.getKeyStroke(key, modifier));
        }
    }

    public VMenuItem(int key, int modifier, String name) {
        this(key, key, modifier, name);
    }
}