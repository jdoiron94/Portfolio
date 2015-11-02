package volupnote.ui.about;

import javax.swing.*;
import java.awt.BorderLayout;

public class AboutFrame extends JDialog {

    public AboutFrame() {
        setTitle("About");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        //setIconImage();
        add(new InformationContainer());
        pack();
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }
}