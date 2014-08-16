package semester_ii.media_tracker;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TrackerPanel extends JPanel {

    public TrackerPanel() {
        setPreferredSize(new Dimension(500, 100));
    }

    @Override
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setFont(Constants.font);
        g.setRenderingHints(Constants.hints);
        FontMetrics metrics = getFontMetrics(g.getFont());
        String text = "Buttons and such for starting, pausing, looping, setting volume, etc., would be placed down here";
        g.drawString(text, (725 - metrics.stringWidth(text)) / 2, (metrics.getAscent() + 100 - (metrics.getAscent() + metrics.getDescent())) / 2);
    }
}