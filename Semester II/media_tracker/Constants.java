package semester_ii.media_tracker;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.RenderingHints;

public class Constants {

    protected static final JPanel[] DEFAULTS = new JPanel[]{new MusicPanel(), new VideoPanel(), new PlaylistPanel()};
    protected static JPanel displayed = DEFAULTS[0];
    protected static final Font FONT = new Font("Arial", Font.PLAIN, 10);
    protected static final RenderingHints HINTS = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    protected static final MediaFrame FRAME = new MediaFrame();
}