package volupnote;

import volupnote.ui.VolupFrame;

import java.awt.*;

public class Boot {

    public static void main(String... args) {
        EventQueue.invokeLater(() -> new VolupFrame().setVisible(true));
    }
}