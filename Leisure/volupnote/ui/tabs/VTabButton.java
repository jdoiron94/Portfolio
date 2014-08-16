package volupnote.ui.tabs;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VTabButton extends JButton {

    private final BasicStroke stroke = new BasicStroke(2);
    private final RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public VTabButton(final VTabbedPane pane, final VTab tab) {
        setPreferredSize(new Dimension(18, 18));
        setToolTipText("Close " + tab.getName());
        setContentAreaFilled(false);
        setFocusable(false);
        setBorderPainted(false);
        setRolloverEnabled(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent event) {
                if (event.getComponent() instanceof AbstractButton) {
                    ((AbstractButton) event.getComponent()).setBorderPainted(true);
                }
            }

            @Override
            public void mouseExited(final MouseEvent event) {
                if (event.getComponent() instanceof AbstractButton) {
                    ((AbstractButton) event.getComponent()).setBorderPainted(false);
                }
            }
        });
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                pane.removeTab(tab);
            }
        });
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics.create();
        g.setStroke(stroke);
        g.setRenderingHints(hints);
        g.setColor(Color.BLACK.darker());
        if (getModel().isRollover()) {
            g.setColor(Color.DARK_GRAY.darker());
        }
        final int delta = 6;
        g.drawLine(delta, delta, getWidth() - 7, getHeight() - 7);
        g.drawLine(getWidth() - 7, delta, delta, getHeight() - 7);
        g.dispose();
    }
}