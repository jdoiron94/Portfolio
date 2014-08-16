package volupnote.ui.view;

import javax.swing.text.Element;
import javax.swing.text.ParagraphView;

public class NoWrapView extends ParagraphView {

    public NoWrapView(final Element element) {
        super(element);
    }

    @Override
    public void layout(final int width, final int height) {
        super.layout(Short.MAX_VALUE, height);
    }

    @Override
    public float getMinimumSpan(final int axis) {
        return super.getPreferredSpan(axis);
    }
}