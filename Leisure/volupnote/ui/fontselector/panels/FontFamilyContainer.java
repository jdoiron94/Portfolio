package volupnote.ui.fontselector.panels;

import volupnote.Context;
import volupnote.ui.fontselector.FontLoader;
import volupnote.ui.fontselector.FontVars;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.util.Set;

public class FontFamilyContainer extends JPanel {

    private final JList<String> fonts = new JList<>();

    public FontFamilyContainer() {
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 10, 10);
        setLayout(layout);
        JLabel fontFamily = new JLabel("Font Family:");
        CellRenderer renderer = new CellRenderer();
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fonts.setCellRenderer(renderer);
        fonts.setFixedCellHeight(25);
        fonts.setModel(getModel(false));
        fonts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                PreviewContainer.setDesiredFontName(fonts.getSelectedValue());
            }
        });
        JScrollPane pane = new JScrollPane(fonts);
        Dimension paneDims = new Dimension(200, 200);
        pane.setPreferredSize(paneDims);
        JLabel refresh = new JLabel(Context.factory.loadIcon("Refresh"));
        refresh.setToolTipText("Refresh the system font list");
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                fonts.setModel(getModel(true));
            }
        });
        pane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, refresh);
        Dimension dimension = new Dimension(210, 230 + fontFamily.getPreferredSize().height);
        setPreferredSize(dimension);
        add(fontFamily);
        add(pane);
    }

    public String getFontName() {
        return fonts.getSelectedValue();
    }

    private DefaultListModel<String> getModel(boolean update) {
        if (FontVars.getFontModel() == null || update) {
            DefaultListModel<String> model = new DefaultListModel<>();
            Set<Font> fonts = FontVars.getFonts() == null || update ? new FontLoader().loadFonts() : FontVars.getFonts();
            for (Font font : fonts) {
                model.addElement(font.getFontName());
            }
            FontVars.setFonts(fonts);
            FontVars.setFontModel(model);
        }
        return FontVars.getFontModel();
    }

    private class CellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Font font = new Font(value.toString(), Font.PLAIN, 15);
            label.setFont(font);
            return label;
        }
    }
}