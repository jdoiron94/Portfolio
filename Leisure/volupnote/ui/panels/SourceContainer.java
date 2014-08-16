package volupnote.ui.panels;

import volupnote.ui.fontselector.FontVars;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

public class SourceContainer extends JPanel {

    private final JEditorPane editor = new JEditorPane();

    public SourceContainer() {
        setLayout(new BorderLayout(0, 0));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            System.err.println("Failed to set theme");
        }
        //editor.setEditorKit(new WrapEditorKit());
        editor.setContentType("text/java");
        editor.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
        editor.setFont(FontVars.currentFont);
        editor.setMargin(new Insets(5, 5, 5, 5));
        add(new JScrollPane(editor), BorderLayout.CENTER);
        setPreferredSize(new Dimension(800, 600));
    }

    public String getText() {
        return editor.getText();
    }

    public void setDesiredFont(final Font font) {
        editor.setFont(font);
    }

    public void setText(final String text) {
        final Document document = editor.getDocument();
        editor.setDocument(new DefaultStyledDocument());
        try {
            document.insertString(0, text, new SimpleAttributeSet());
        } catch (final BadLocationException ignored) {
            System.err.println("Error setting text");
        }
        editor.setDocument(document);
    }
}