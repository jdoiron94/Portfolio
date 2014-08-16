package semester_ii.visual_media_tracker;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Simon's Media Tracker");
        setLayout(new BorderLayout(0, 0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ignored) {
            System.out.println("Could not set the system look and feel");
        }
        JMenu insert = new JMenu("Insert");
        JMenuItem addColumn = new JMenuItem("Add column");
        addColumn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String entered = JOptionPane.showInputDialog(null, "Enter a name for the column:", "Add new column", JOptionPane.INFORMATION_MESSAGE);
                if (entered != null && !entered.replace(" ", "").isEmpty()) {
                    Panels.musicFeed.addColumn(entered);
                }
            }
        });
        insert.add(addColumn);
        JMenu newMenu = new JMenu("New");
        JMenuItem playlist = new JMenuItem("Playlist");
        playlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String entered = JOptionPane.showInputDialog(null, "Enter a playlist name:", "New playlist", JOptionPane.INFORMATION_MESSAGE);
                if (entered != null && !entered.replace(" ", "").isEmpty()) {
                    Panels.playlistFeed.addPlaylist(entered);
                }
            }
        });
        newMenu.add(playlist);
        JMenu item = new JMenu("Item...");
        JMenuItem cd = new JMenuItem("CD");
        cd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd add a new CD, assuming we supported it");
            }
        });
        item.add(cd);
        JMenuItem dvd = new JMenuItem("DVD");
        dvd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd add a new DVD, assuming we supported it");
            }
        });
        item.add(dvd);
        newMenu.add(item);
        JMenu view = new JMenu("View");
        JMenu changeFormat = new JMenu("Change format...");
        JMenuItem newsfeed = new JMenuItem("Newsfeed");
        newsfeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd switch to a newsfeed style");
            }
        });
        changeFormat.add(newsfeed);
        JMenuItem orderByUser = new JMenuItem("Order by user");
        orderByUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd switch to an order-by-user style");
            }
        });
        changeFormat.add(orderByUser);
        view.add(changeFormat);
        JMenu export = new JMenu("Export");
        JMenuItem toADevice = new JMenuItem("To a device");
        toADevice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd export to a device");
            }
        });
        export.add(toADevice);
        JMenuItem email = new JMenuItem("Email");
        email.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd export to an email");
            }
        });
        export.add(email);
        JMenuItem twitter = new JMenuItem("Twitter");
        twitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd export to Twitter");
            }
        });
        export.add(twitter);
        JMenuItem facebook = new JMenuItem("Facebook");
        facebook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd export to Facebook");
            }
        });
        export.add(facebook);
        JMenuItem other = new JMenuItem("Other");
        other.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Here's where we'd export to some other kind of storage medium");
            }
        });
        export.add(other);
        JMenuBar bar = new JMenuBar();
        bar.add(insert);
        bar.add(newMenu);
        bar.add(view);
        bar.add(export);
        setJMenuBar(bar);
        add(Panels.playlistFeed, BorderLayout.WEST);
        add(Panels.shown, BorderLayout.CENTER);
        add(new SearchFeed(), BorderLayout.SOUTH);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    protected void switchPanels(JPanel newPanel) {
        //remove(Panels.shown);
        Panels.shown = newPanel;
        //add(Panels.shown, BorderLayout.CENTER);
        //repaint();
        System.out.println("Here is where we would have switched to the " + Panels.shown.getClass().getSimpleName() + " panel");
    }
}