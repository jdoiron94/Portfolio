package volupnote.ui.menubar.menus;

import javax.swing.JMenu;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class VMenu extends JMenu {

    public VMenu(final String name, final int mnemonic) {
        super(name);
        setMnemonic(mnemonic);
        addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    requestFocus();
                }
            }
        });
    }
}