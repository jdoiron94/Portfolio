package rex;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RexFrame extends JFrame {

    private final JTextField regex = new JTextField();
    private final JTextField string = new JTextField();
    private int matches = -1;

    public RexFrame() {
        setTitle("Rex");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ignored) {
            System.err.println("Unable to set system look and feel");
        }
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 5)));
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        north.add(Box.createRigidArea(new Dimension(5, 0)));
        north.add(new JLabel("Regex expression:"));
        north.add(Box.createRigidArea(new Dimension(30, 0)));
        //JTextField regex = new JTextField();
        regex.setPreferredSize(new Dimension(130, 20));
        north.add(regex);
        north.add(Box.createRigidArea(new Dimension(5, 0)));
        add(north);
        add(Box.createRigidArea(new Dimension(0, 5)));
        JPanel south = new JPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
        south.add(Box.createRigidArea(new Dimension(5, 0)));
        south.add(new JLabel("Regex string:"));
        south.add(Box.createRigidArea(new Dimension(30, 0)));
        //JTextField string = new JTextField();
        string.setPreferredSize(new Dimension(130, 20));
        south.add(string);
        south.add(Box.createRigidArea(new Dimension(5, 0)));
        add(south);
        add(Box.createRigidArea(new Dimension(0, 5)));
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        JButton test = new JButton("Run");
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                matches = 0;
                Matcher matcher = Pattern.compile(regex.getText()).matcher(string.getText());
                while (matcher.find()) {
                    matches++;
                }
                setTitle("Rex - " + (matches == 1 ? "1 match" : matches + " matches"));
            }
        });
        right.add(test);
        add(right);
        add(Box.createRigidArea(new Dimension(0, 5)));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
}