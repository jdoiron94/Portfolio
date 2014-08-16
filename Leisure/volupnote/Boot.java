package volupnote;

import volupnote.ui.VolupFrame;

import java.awt.EventQueue;

public class Boot {

    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VolupFrame().setVisible(true);
            }
        });
    }
}