package volupnote.ui.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.ByteArrayOutputStream;

public class OutputContainer extends JPanel {

    public OutputContainer() {
        setLayout(new BorderLayout(0, 0));
        Console console = new Console();
        JScrollPane pane = new JScrollPane(console.getPane());
        add(pane, BorderLayout.CENTER);
        Dimension dimension = new Dimension(800, 150);
        setPreferredSize(dimension);
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