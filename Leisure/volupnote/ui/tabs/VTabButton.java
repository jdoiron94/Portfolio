package volupnote.ui.tabs;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VTabButton extends JButton {

    private final BasicStroke stroke = new BasicStroke(2);
    private final RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public VTabButton(final VTabbedPane pane, final VTab tab) {
        Dimension dimension = new Dimension(18, 18);
        setPreferredSize(dimension);
        setToolTipText("Close " + tab.getName());
        setContentAreaFilled(false);
        setFocusable(false);
        setBorderPainted(false);
        setRolloverEnabled(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                if (event.getComponent() instanceof AbstractButton) {
                    ((AbstractButton) event.getComponent()).setBorderPainted(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent event) {
                if (event.getComponent() instanceof AbstractButton) {
                    ((AbstractButton) event.getComponent()).setBorderPainted(false);
                }
            }
        });
        addActionListener(event -> pane.removeTab(tab));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setStroke(stroke);
        g.setRenderingHints(hints);
        g.setColor(Color.BLACK.darker());
        if (getModel().isRollover()) {
            g.setColor(Color.DARK_GRAY.darker());
        }
        int delta = 6;
        g.drawLine(delta, delta, getWidth() - 7, getHeight() - 7);
        g.drawLine(getWidth() - 7, delta, delta, getHeight() - 7);
        g.dispose();
    }
}