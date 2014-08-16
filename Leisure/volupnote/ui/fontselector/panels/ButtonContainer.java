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
        final JButton apply = new JButton("Apply", Context.FACTORY.loadIcon("Apply"));
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                FontVars.currentFont = selector.getSelectedFont();
                for (final VTab tab : VolupFrame.getTabContainer().getTabs()) {
                    tab.getEditor().setDesiredFont(FontVars.currentFont);
                }
                selector.dispose();
            }
        });
        final JButton cancel = new JButton("Cancel", Context.FACTORY.loadIcon("Cancel"));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                selector.dispose();
            }
        });
        setPreferredSize(new Dimension(420, 20 + apply.getPreferredSize().height));
        add(cancel);
        add(apply);
    }
}