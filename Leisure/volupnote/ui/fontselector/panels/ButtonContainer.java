package volupnote.ui.fontselector.panels;

import volupnote.Context;
import volupnote.ui.VolupFrame;
import volupnote.ui.fontselector.FontSelector;
import volupnote.ui.fontselector.FontVars;
import volupnote.ui.tabs.VTab;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class ButtonContainer extends JPanel {

    public ButtonContainer(final FontSelector selector) {
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT, 10, 10);
        setLayout(layout);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JButton apply = new JButton("Apply", Context.factory.loadIcon("Apply"));
        apply.addActionListener(event -> {
            FontVars.setCurrentFont(selector.getSelectedFont());
            for (VTab tab : VolupFrame.getTabContainer().getTabs()) {
                tab.getEditor().setDesiredFont(FontVars.getCurrentFont());
            }
            selector.dispose();
        });
        JButton cancel = new JButton("Cancel", Context.factory.loadIcon("Cancel"));
        cancel.addActionListener(event -> selector.dispose());
        Dimension size = new Dimension(420, 20 + apply.getPreferredSize().height);
        setPreferredSize(size);
        add(cancel);
        add(apply);
    }
}