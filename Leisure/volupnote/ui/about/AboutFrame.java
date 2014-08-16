package volupnote.ui.about;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class AboutFrame extends JFrame {

    public AboutFrame() {
        setTitle("About");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        //setIconImage();
        add(new InformationContainer());
        pack();
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }
}