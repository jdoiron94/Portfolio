package semester_ii.media_tracker;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MediaFrame extends JFrame {

    public MediaFrame() {
        setTitle("Media Tracker");
        setLayout(new BorderLayout(0, 0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored) {
            System.err.print("Couldn't set the look and feel to system default");
        }
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenu newMenu = new JMenu("New...");
        newMenu.setMnemonic(KeyEvent.VK_N);
        JMenuItem newMusicPlaylist = new JMenuItem("Music Playlist");
        newMusicPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Where you'd open a dialog and ask for a new name for the music playlist before adding a new tab to the left");
            }
        });
        newMusicPlaylist.setMnemonic(KeyEvent.VK_M);
        newMenu.add(newMusicPlaylist);
        JMenuItem newVideoPlaylist = new JMenuItem("Video Playlist");
        newVideoPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Where you'd open a dialog and ask for a new name for the video playlist before adding a new tab to the left");
            }
        });
        newVideoPlaylist.setMnemonic(KeyEvent.VK_V);
        newMenu.add(newVideoPlaylist);
        JMenuItem newPodcastPlaylist = new JMenuItem("Podcast Playlist");
        newPodcastPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Where you'd open a dialog and ask for a new name for the podcast playlist before adding a new tab to the left");
            }
        });
        newPodcastPlaylist.setMnemonic(KeyEvent.VK_P);
        newMenu.add(newPodcastPlaylist);
        file.add(newMenu);
        file.addSeparator();
        JMenuItem hide = new JMenuItem("Hide to tray...");
        hide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Where you'd use TrayIcon and SystemTray to hide to the system tray, if supported");
            }
        });
        hide.setMnemonic(KeyEvent.VK_H);
        file.add(hide);
        file.addSeparator();
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting on error code: 0");
                System.exit(0);
            }
        });
        exit.setMnemonic(KeyEvent.VK_E);
        file.add(exit);
        JMenuBar bar = new JMenuBar();
        bar.add(file);
        setJMenuBar(bar);
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        top.add(new PlaylistPanel());
        top.add(Constants.displayed);
        add(top, BorderLayout.CENTER);
        add(new TrackerPanel(), BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void add(Component component, String restriction) {
        component.setFocusable(false);
        getContentPane().add(component, restriction);
    }
}