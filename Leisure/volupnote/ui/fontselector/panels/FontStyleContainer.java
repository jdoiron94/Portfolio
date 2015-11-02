package volupnote.ui.fontselector.panels;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class FontStyleContainer extends JPanel {

    private final JList<String> styles = new JList<>();

    public FontStyleContainer() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel fontStyle = new JLabel("Font Style:");
        styles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        styles.setCellRenderer(new CellRenderer());
        styles.setFixedCellHeight(25);
        DefaultListModel<String> model = new DefaultListModel<>();
        Object[][] options = {{"Regular", Font.PLAIN}, {"Italic", Font.ITALIC}, {"Bold", Font.BOLD}, {"Bold Italic", Font.BOLD | Font.ITALIC}};
        for (Object[] option : options) {
            model.addElement((String) option[0]);
        }
        styles.setModel(model);
        styles.setSelectedIndex(0);
        styles.addListSelectionListener(event -> PreviewContainer.setDesiredFontStyle(getFontStyle()));
        JScrollPane stylePane = new JScrollPane(styles);
        stylePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        stylePane.setPreferredSize(new Dimension(150, 200));
        setPreferredSize(new Dimension(160, 220 + fontStyle.getPreferredSize().height));
        add(fontStyle);
        add(stylePane);
    }

    public int getFontStyle() {
        switch (styles.getSelectedIndex()) {
            case 0:
                return Font.PLAIN;
            case 1:
                return Font.ITALIC;
            case 2:
                return Font.BOLD;
            case 3:
                return Font.BOLD | Font.ITALIC;
            default:
                return Font.PLAIN;
        }
    }

    private class CellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            int attribute;
            switch (value.toString()) {
                case "Regular":
                    attribute = Font.PLAIN;
                    break;
                case "Italic":
                    attribute = Font.ITALIC;
                    break;
                case "Bold":
                    attribute = Font.BOLD;
                    break;
                case "Bold Italic":
                    attribute = Font.BOLD | Font.ITALIC;
                    break;
                default:
                    attribute = Font.PLAIN;
            }
            label.setFont(new Font(getFont().getFontName(), attribute, 15));
            return label;
        }
    }
}