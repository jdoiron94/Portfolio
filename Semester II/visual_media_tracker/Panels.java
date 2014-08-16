package semester_ii.visual_media_tracker;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class Panels {

    protected static JPanel shown = new MusicFeed();
    protected static final PlaylistFeed playlistFeed = new PlaylistFeed();
    protected static final MusicFeed musicFeed = new MusicFeed();
    protected static final MainFrame frame = new MainFrame();
    protected static final List<JPanel> panels = new ArrayList<>(5);

    static {
        panels.add(musicFeed);
        panels.add(new VideoFeed());
        panels.add(new PictureFeed());
    }
}