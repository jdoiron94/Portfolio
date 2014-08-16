package pokemon.daycare;

import java.awt.EventQueue;

public class DaycareAid {

    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DaycareUI().setVisible(true);
            }
        });
    }
}