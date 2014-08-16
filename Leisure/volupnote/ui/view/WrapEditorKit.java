package volupnote.ui.view;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

public class WrapEditorKit extends StyledEditorKit {

    private final ViewFactory defaultFactory = new WrapColumnFactory();

    @Override
    public ViewFactory getViewFactory() {
        return defaultFactory;
    }

    @Override
    public MutableAttributeSet getInputAttributes() {
        MutableAttributeSet attributes = super.getInputAttributes();
        attributes.removeAttribute("line_break_attribute");
        return attributes;
    }
}