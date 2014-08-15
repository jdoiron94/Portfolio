package semester_ii.media_tracker;

import javax.swing.SwingUtilities;

public class MediaTracker {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Constants.FRAME.setVisible(true);
            }
        });
    }
}