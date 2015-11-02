package volupnote.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputContainer extends JPanel {

    public OutputContainer() {
        setLayout(new BorderLayout(0, 0));
        add(new JScrollPane(new Console().getPane()), BorderLayout.CENTER);
        setPreferredSize(new Dimension(800, 150));
    }

    public class Console extends ByteArrayOutputStream {

        private final JTextPane pane;

        public Console() {
            pane = new JTextPane();
            pane.setEditable(false);
            //System.setErr(new PrintStream(this, true));
            //System.setOut(new PrintStream(this, true));
        }

        public JTextPane getPane() {
            return pane;
        }
    }
}