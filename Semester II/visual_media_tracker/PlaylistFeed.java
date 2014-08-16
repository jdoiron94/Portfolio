package semester_ii.visual_media_tracker;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PlaylistFeed extends JPanel {

    private final JList<String> playlists = new JList<>(new String[]{"Music", "Videos", "Pictures"});

    public PlaylistFeed() {
        setLayout(new BorderLayout(0, 0));
        setPreferredSize(new Dimension(150, 400));
        playlists.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Panels.frame.switchPanels(Panels.panels.get(playlists.getSelectedIndex()));
            }
        });
        JScrollPane scrollPane = new JScrollPane(playlists);
        scrollPane.setPreferredSize(new Dimension(150, 380));
        add(scrollPane, BorderLayout.CENTER);
    }

    protected void addPlaylist(String playlist) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < playlists.getModel().getSize(); i++) {
            model.addElement(playlists.getModel().getElementAt(i));
        }
        model.addElement(playlist);
        playlists.setModel(model);
        Panels.panels.add(new JPanel());
    }
}