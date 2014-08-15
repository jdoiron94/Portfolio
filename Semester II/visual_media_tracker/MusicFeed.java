package semester_ii.visual_media_tracker;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MusicFeed extends JPanel {

    public MusicFeed() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{{"Song", "Someone", "Album", "Something", "1994"}}, new Object[]{"Name", "Artist", "Album", "Genre", "Year"});
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        add(scrollPane);
    }

    public void addColumn(String column) {
        //model.addColumn(column);
        //table.setModel(model);
        //repaint();
        System.out.println("Here's where we'd add \"" + column + "\" as a column if this were a real application");
    }
}