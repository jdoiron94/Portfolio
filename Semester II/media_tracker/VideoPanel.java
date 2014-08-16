package semester_ii.media_tracker;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class VideoPanel extends JPanel {

    public VideoPanel() {
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setFont(Constants.font);
        g.setRenderingHints(Constants.hints);
        FontMetrics metrics = g.getFontMetrics();
        String text = "Here is where the videos would be displayed.  They would be in a tile fashion,\nthree per row, displaying a thumbnail of the video as well\nas the name of the video beneath it.";
        g.drawString(text, (725 - metrics.stringWidth(text)) / 2, (metrics.getAscent() + 100 - (metrics.getAscent() + metrics.getDescent())) / 2);
    }
}