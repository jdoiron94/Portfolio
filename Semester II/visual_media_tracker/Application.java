package semester_ii.visual_media_tracker;

import java.awt.EventQueue;

public class Application {

    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Panels.frame.setVisible(true);
            }
        });
    }
}