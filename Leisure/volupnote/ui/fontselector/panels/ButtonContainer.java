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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonContainer extends JPanel {

    public ButtonContainer(final FontSelector selector) {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
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
        setPreferredSize(new Dimension(420, 20 + apply.getPreferredSize().height));
        add(cancel);
        add(apply);
    }
}