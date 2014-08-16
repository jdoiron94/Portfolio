package volupnote.ui.fontselector.panels;

import volupnote.ui.fontselector.FontVars;
import volupnote.ui.view.CenteredEditorKit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class PreviewContainer extends JPanel {

	private static final JTextPane preview = new JTextPane();

	public PreviewContainer() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(420, 100));
		setBorder(BorderFactory.createTitledBorder("Preview:"));
		preview.setEditorKit(new CenteredEditorKit());
		preview.setFont(FontVars.currentFont);
		preview.setText("aAbBcCdDeEfF");
		preview.setEditable(false);
		final JScrollPane pane = new JScrollPane(preview);
		pane.setPreferredSize(new Dimension(420, 100));
		add(pane, BorderLayout.CENTER);
	}

	protected static Font getCurrentFont() {
		return preview.getFont();
	}

	protected static void setDesiredFontStyle(final int style) {
		preview.setFont(preview.getFont().deriveFont(style));
	}

	protected static void setDesiredFontSize(final float size) {
		preview.setFont(preview.getFont().deriveFont(size));
	}

	protected static void setDesiredFontName(final String name) {
		preview.setFont(new Font(name, preview.getFont().getStyle(), preview.getFont().getSize()));
	}
}