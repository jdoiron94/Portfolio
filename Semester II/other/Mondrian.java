package semester_ii.other;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mondrian extends JFrame {

    private static final Stroke stroke = new BasicStroke(5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

    public Mondrian() {
        setTitle("Mondrian Faux");
        setLayout(new BorderLayout(0, 0));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored) {
            System.out.println("Could not set system look and feel");
        }
        final Painting painting = new Painting(new Rectangle(0, 0, 510, 510));
        add(painting, BorderLayout.NORTH);
        JButton button = new JButton("Repaint");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                painting.repaint();
            }
        });
        button.setFocusable(false);
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        bottom.add(button);
        add(bottom, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private static int nextInt(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    private static void paint(Graphics2D g, Rectangle bounds, boolean first) {
        g.setStroke(stroke);
        if (first) {
            g.draw(bounds);
        }
        if (bounds.width > 30 && bounds.height > 30) {
            Rectangle paintable = new Rectangle(bounds.x, bounds.y, bounds.width, nextInt(bounds.height / 2, bounds.height));
            g.draw(paintable);
            paint(g, paintable, false);
        } else {
            g.setColor(new Color((int) (Math.random() * 16777216)));
            g.fillRect(bounds.x + 3, bounds.y + 3, bounds.width - 5, bounds.height - 5);
        }
        g.setColor(Color.BLACK);
    }

    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Mondrian().setVisible(true);
            }
        });
    }

    private class Painting extends JPanel {

        private final Rectangle bounds;

        public Painting(Rectangle bounds) {
            this.bounds = bounds;
            setPreferredSize(new Dimension(bounds.width, bounds.height));
        }

        @Override
        public Rectangle getBounds() {
            return bounds;
        }

        @Override
        public void paintComponent(Graphics g1) {
            super.paintComponent(g1);
            Graphics2D g = (Graphics2D) g1;
            Rectangle one;
            Rectangle two;
            if ((int) (Math.random() * 2) % 2 == 0) {
                one = new Rectangle(5, 5, nextInt(bounds.width / 4, bounds.width / 2), bounds.height - 10);
                two = new Rectangle(5 + one.width, 5, bounds.width - one.width, bounds.height - 10);
            } else {
                one = new Rectangle(5, 5, bounds.width, nextInt(bounds.height / 4, bounds.height / 2));
                two = new Rectangle(5, 5 + one.height, bounds.width, bounds.height - one.height - 10);
            }
            Mondrian.paint(g, one, true);
            Mondrian.paint(g, two, true);
        }
    }
}