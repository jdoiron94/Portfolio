package semester_ii.media_tracker;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MusicPanel extends JPanel {

    public MusicPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(null, new String[]{"Name", "Artist", "Album", "Genre", "Bitrate"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addRow(new Object[]{"Cynical", "Mr FijiWiji, CoMa", "Aftermath", "Dubstep", "320"});
        model.addRow(new Object[]{"Hummingbird", "Tut Tut Child, Augustus Ghost", "Aftermath", "Dubstep", "320"});
        table.setModel(model);
        table.setTableHeader(new ImmovableTable(table.getColumnModel()));
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(500, 500));
        add(pane);
    }

    private class ImmovableTable extends JTableHeader {

        public ImmovableTable(TableColumnModel model) {
            super(model);
        }

        @Override
        public void setDraggedColumn(TableColumn column) {
            super.setDraggedColumn(null);
        }
    }
}