package pokemon.daycare;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntegerField extends JTextField {

    public IntegerField(int columns) {
        super(columns);
    }

    public IntegerField() {
    }

    @Override
    protected Document createDefaultModel() {
        return new LetterFilter();
    }

    public class LetterFilter extends PlainDocument {

        @Override
        public void insertString(int offset, String string, AttributeSet set) throws BadLocationException {
            if (string != null) {
                for (char c : string.toCharArray()) {
                    try {
                        Integer.parseInt(Character.toString(c));
                    } catch (NumberFormatException ignored) {
                        return;
                    }
                }
                super.insertString(offset, string, set);
            }
        }
    }
}