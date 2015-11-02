package volupnote.ui;

import volupnote.Context;
import volupnote.ui.fontselector.FontLoader;
import volupnote.ui.menubar.MenuBar;
import volupnote.ui.panels.OutputContainer;
import volupnote.ui.tabs.VTab;
import volupnote.ui.tabs.VTabbedPane;

import javax.swing.*;
import java.awt.*;

public class VolupFrame extends JFrame {

    private static final VTabbedPane tabContainer = new VTabbedPane();

    public VolupFrame() {
        setTitle("VolupNote v0.1a");
        setLayout(new BorderLayout(0, 0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Context.factory.loadIcon("VolupNote").getImage());
        Context.setFontLoader(new FontLoader());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            System.err.println("Failed to set theme");
        }
        add(tabContainer, BorderLayout.CENTER);
        add(new OutputContainer(), BorderLayout.SOUTH);
        SwingUtilities.updateComponentTreeUI(this);
        setJMenuBar(new MenuBar());
        setPreferredSize(new Dimension(800, 750));
        pack();
        //setResizable(false);
        setLocationRelativeTo(null);
    }

    public static VTabbedPane getTabContainer() {
        return tabContainer;
    }

    public static void addTab(VTab tab) {
        tabContainer.addTab(tab);
        tabContainer.setSelectedIndex(tabContainer.getTabCount() - 1);
    }

    public static void removeTab(VTab tab) {
        tabContainer.removeTab(tab);
    }
}