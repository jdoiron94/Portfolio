package volupnote.ui.tabs;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class VTabPanel extends JPanel {

    public VTabPanel(VTabbedPane pane, VTab tab) {
        FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 0, 0);
        setLayout(layout);
        setOpaque(false);
        JLabel label = new JLabel(tab.getName());
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        add(label);
        VTabButton button = new VTabButton(pane, tab);
        add(button);
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
}