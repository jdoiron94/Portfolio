package volupnote.ui.fontselector.panels;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FontSizeContainer extends JPanel {

	private final List<Integer> sizes = new ArrayList<>();
	private final JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(15, 8, 72, 1));

	public FontSizeContainer() {
		final Integer[] FONT_SIZES = new Integer[]{8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
		Collections.addAll(sizes, FONT_SIZES);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
		final JLabel sizeLabel = new JLabel("Size:");
		((JSpinner.DefaultEditor) sizeSpinner.getEditor()).getTextField().setEditable(false);
		sizeSpinner.setPreferredSize(new Dimension(50, 25));
		final JList<Integer> sizeList = new JList<>(FONT_SIZES);
		sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeList.setFixedCellHeight(25);
		sizeList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(final ListSelectionEvent e) {
				System.out.println("JList valueChanged event fired");
				sizeSpinner.setValue(sizeList.getSelectedValue());
			}
		});
		sizeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent e) {
				System.out.println("Spinner stateChanged event fired");
				PreviewContainer.setDesiredFontSize((float) getFontSize());
				final int index = sizes.indexOf(getFontSize());
				if (index >= 0) {
					sizeList.setSelectedIndex(index);
					sizeList.ensureIndexIsVisible(index);
				}
			}
		});
		final JScrollPane sizePane = new JScrollPane(sizeList);
		sizePane.setPreferredSize(new Dimension(50, 165));
		setPreferredSize(new Dimension(60, 30 + sizeLabel.getPreferredSize().height + sizeSpinner.getPreferredSize().height + sizePane.getPreferredSize().height));
		add(sizeLabel);
		add(sizeSpinner);
		add(sizePane);
	}

	public int getFontSize() {
		return (Integer) sizeSpinner.getValue();
	}
}