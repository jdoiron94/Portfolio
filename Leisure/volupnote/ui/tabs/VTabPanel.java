package volupnote.ui.tabs;

import javax.swing.*;
import java.awt.*;

public class VTabPanel extends JPanel {

    public VTabPanel(VTabbedPane pane, VTab tab) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);
        JLabel label = new JLabel(tab.getName());
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        add(label);
        add(new VTabButton(pane, tab));
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
}