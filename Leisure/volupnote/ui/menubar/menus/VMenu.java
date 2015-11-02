package volupnote.ui.menubar.menus;

import javax.swing.JMenu;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VMenu extends JMenu {

    public VMenu(String name, int mnemonic) {
        super(name);
        setMnemonic(mnemonic);
        addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                requestFocus();
            }
        });
    }
}