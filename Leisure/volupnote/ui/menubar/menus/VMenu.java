package volupnote.ui.menubar.menus;

import javax.swing.*;
import java.awt.event.ItemEvent;

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