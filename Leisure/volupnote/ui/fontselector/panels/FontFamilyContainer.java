package volupnote.ui.fontselector.panels;

import volupnote.Context;
import volupnote.ui.fontselector.FontLoader;
import volupnote.ui.fontselector.FontVars;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class FontFamilyContainer extends JPanel {

    private final JList<String> fonts = new JList<>();

    public FontFamilyContainer() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel fontFamily = new JLabel("Font Family:");
        fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fonts.setCellRenderer(new CellRenderer());
        fonts.setFixedCellHeight(25);
        fonts.setModel(getModel(false));
        fonts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                PreviewContainer.setDesiredFontName(fonts.getSelectedValue());
            }
        });
        JScrollPane pane = new JScrollPane(fonts);
        pane.setPreferredSize(new Dimension(200, 200));
        JLabel refresh = new JLabel(Context.factory.loadIcon("Refresh"));
        refresh.setToolTipText("Refresh the system font list");
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                fonts.setModel(getModel(true));
            }
        });
        pane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, refresh);
        setPreferredSize(new Dimension(210, 230 + fontFamily.getPreferredSize().height));
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
            label.setFont(new Font(value.toString(), Font.PLAIN, 15));
            return label;
        }
    }
}