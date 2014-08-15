package semester_ii.visual_media_tracker;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class Panels {

    protected static JPanel shown = new MusicFeed();

    protected static PlaylistFeed playlistFeed = new PlaylistFeed();
    protected static MusicFeed musicFeed = new MusicFeed();

    protected static List<JPanel> panels = new ArrayList<JPanel>();

    static {
        panels.add(musicFeed);
        panels.add(new VideoFeed());
        panels.add(new PictureFeed());
    }

    protected static MainFrame frame = new MainFrame();
}