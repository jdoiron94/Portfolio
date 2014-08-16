package semester_ii.visual_media_tracker;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchFeed extends JPanel {

    public SearchFeed() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton addPlaylist = new JButton("Playlist (+)");
        addPlaylist.setPreferredSize(new Dimension(150, 20));
        addPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String entered = JOptionPane.showInputDialog(null, "Enter a name for the playlist:", "New Playlist", JOptionPane.INFORMATION_MESSAGE);
                if (entered != null && !entered.replace(" ", "").isEmpty()) {
                    Panels.playlistFeed.addPlaylist(entered);
                }
            }
        });
        add(addPlaylist);
        final JTextField field = new JTextField("Search...");
        field.setPreferredSize(new Dimension(600, 20));
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here is where if we had functionality, we'd search through playlists for: \"" + field.getText() + "\"");
            }
        });
        add(field);
    }
}