package volupnote.ui.panels;

import volupnote.ui.fontselector.FontVars;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

public class SourceContainer extends JPanel {

    private final JEditorPane editor = new JEditorPane();

    public SourceContainer() {
        BorderLayout layout = new BorderLayout(0, 0);
        setLayout(layout);
        try { // move to before eventqueue invoke, do mac title bar property
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            System.err.println("Failed to set theme");
        }
        Insets inset = new Insets(5, 5, 5, 5);
        //editor.setEditorKit(new WrapEditorKit());
        editor.setContentType("text/java");
        editor.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
        editor.setFont(FontVars.getCurrentFont());
        editor.setMargin(inset);
        JScrollPane pane = new JScrollPane(editor);
        add(pane, BorderLayout.CENTER);
        Dimension dimension = new Dimension(800, 600);
        setPreferredSize(dimension);
    }

    public String getText() {
        return editor.getText();
    }

    public void setDesiredFont(Font font) {
        editor.setFont(font);
    }

    public void setText(String text) {
        Document document = editor.getDocument();
        DefaultStyledDocument styled = new DefaultStyledDocument();
        editor.setDocument(styled);
        try {
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            document.insertString(0, text, attributes);
        } catch (BadLocationException ignored) {
            System.err.println("Error setting text");
        }
        editor.setDocument(document);
    }
}