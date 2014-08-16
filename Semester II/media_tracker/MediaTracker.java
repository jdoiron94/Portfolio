package semester_ii.media_tracker;

import java.awt.EventQueue;

public class MediaTracker {

    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Constants.frame.setVisible(true);
            }
        });
    }
}