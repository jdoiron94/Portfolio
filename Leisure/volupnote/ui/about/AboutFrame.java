package volupnote.ui.about;

import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class AboutFrame extends JDialog {

    public AboutFrame() {
        setTitle("About");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        //setIconImage();
        InformationContainer container = new InformationContainer();
        add(container);
        pack();
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }
}