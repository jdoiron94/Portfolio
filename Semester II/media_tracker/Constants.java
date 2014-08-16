package semester_ii.media_tracker;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.RenderingHints;

public class Constants {

    protected static final JPanel[] defaults = new JPanel[]{new MusicPanel(), new VideoPanel(), new PlaylistPanel()};
    protected static JPanel displayed = defaults[0];
    protected static final Font font = new Font("Arial", Font.PLAIN, 10);
    protected static final RenderingHints hints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    protected static final MediaFrame frame = new MediaFrame();
}