package semester_ii.media_tracker;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class PlaylistPanel extends JPanel {

    public PlaylistPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        final JList<String> list = new JList<>(new String[]{"Default Music Playlist", "Default Video Playlist", "Default Podcast Playlist"});
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                //Constants.frame.remove(Constants.displayed);
                Constants.displayed = Constants.defaults[list.getSelectedIndex()];
                //Constants.frame.add(Constants.displayed, BorderLayout.CENTER);
                //Constants.frame.repaint();
                System.out.println("Would've been updated to: " + Constants.defaults[list.getSelectedIndex()].getClass().getSimpleName());
            }
        });
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(200, 500));
        add(pane);
    }
}