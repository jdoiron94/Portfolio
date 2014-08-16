package pokemon.daycare;

import com.sun.corba.se.impl.io.TypeMismatchException;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class IntegerField extends JTextField {

    public IntegerField() {
        super();
    }

    public IntegerField(int columns) {
        super(columns);
    }

    @Override
    protected Document createDefaultModel() {
        return new LetterFilter();
    }

    public class LetterFilter extends PlainDocument {

        @Override
        public void insertString(int offset, String string, AttributeSet set) throws BadLocationException{
            if (string != null) {
                for (char c : string.toCharArray()) {
                    try {
                        Integer.parseInt(Character.toString(c));
                    } catch (NumberFormatException e) {
                        return;
                    }
                }
                super.insertString(offset, string, set);
            }
        }
    }
}