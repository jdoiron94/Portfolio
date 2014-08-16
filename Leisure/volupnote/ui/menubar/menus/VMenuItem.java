package volupnote.ui.menubar.menus;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class VMenuItem extends JMenuItem {

    public VMenuItem(final String name, final int mnemonic, final int key, final int modifier) {
        super(mnemonic != KeyEvent.VK_UNDEFINED && key != KeyEvent.VK_UNDEFINED && modifier != KeyEvent.VK_UNDEFINED ? name + "      " : name);
        if (mnemonic != KeyEvent.VK_UNDEFINED) {
            setMnemonic(mnemonic);
        }
        if (key != KeyEvent.VK_UNDEFINED && modifier != KeyEvent.VK_UNDEFINED) {
            setAccelerator(KeyStroke.getKeyStroke(key, modifier));
        }
    }

    public VMenuItem(final String name, final int key, final int modifier) {
        this(name, key, key, modifier);
    }
}