package pokemon.daycare;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class DaycareUI extends JFrame {

    public DaycareUI() {
        setTitle("Lappy");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            System.out.println("Could not load system look and feel");
        }
        setLayout(new BorderLayout());
        /*JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));*/
        Box left = Box.createVerticalBox();
        Box right = Box.createVerticalBox();
        JLabel pokemon = new JLabel("Pokemon:");
        left.add(Box.createVerticalGlue());
        add(pokemon, left);
        left.add(Box.createVerticalGlue());
        final JComboBox<String> pokemonList = new JComboBox<>(Holder.pokemonNames);
        right.add(Box.createVerticalGlue());
        add(pokemonList, right);
        right.add(Box.createVerticalGlue());
        JLabel experience = new JLabel("Experience:");
        add(experience, left);
        left.add(Box.createVerticalGlue());
        final IntegerField experienceField = new IntegerField();
        add(experienceField, right);
        right.add(Box.createVerticalGlue());
        JLabel level = new JLabel("Level:");
        add(level, left);
        left.add(Box.createVerticalGlue());
        final JSpinner levelSpinner = new JSpinner(new SpinnerNumberModel(99, 1, 99, 1));
        levelSpinner.setEditor(new JSpinner.DefaultEditor(levelSpinner));
        ((JSpinner.DefaultEditor) levelSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
        add(levelSpinner, right);
        right.add(Box.createVerticalGlue());
        JLabel path = new JLabel("Path Size:");
        add(path, left);
        left.add(Box.createVerticalGlue());
        final IntegerField pathField = new IntegerField();
        add(pathField, right);
        right.add(Box.createVerticalGlue());
        System.out.println(pathField);
        JButton submit = new JButton("Submit");
        final JLabel laps = new JLabel("#### laps");
        System.out.print(Holder.binarySearch("Abra"));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Relation relation = Holder.pokemon[Holder.binarySearch((String) pokemonList.getSelectedItem())].getRelation();
                int lapz = (relation.getExpTable()[(Integer) levelSpinner.getValue() - 1] - Integer.parseInt(experienceField.getText())) / Integer.parseInt(pathField.getText());
                laps.setText(NumberFormat.getInstance().format(lapz) + (lapz == 1 ? " lap" : " laps"));
            }
        });
        add(submit, left);
        add(laps, right);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        pack();
        System.out.println(getSize());
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void add(JComponent component, Container container) {
        component.setPreferredSize(new Dimension(component.getPreferredSize().width, 20));
        container.add(component);
    }
}