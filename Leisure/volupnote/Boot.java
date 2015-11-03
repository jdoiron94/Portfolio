package volupnote;

import volupnote.ui.VolupFrame;
import volupnote.util.OperatingSystem;

import javax.swing.UIManager;
import java.awt.EventQueue;

public class Boot {

    public static void main(String... args) {
        if (OperatingSystem.getSystem() == OperatingSystem.MAC) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        } else {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                System.err.println("Could not set system look and feel.");
            }
        }
        EventQueue.invokeLater(() -> new VolupFrame().setVisible(true));
    }
}