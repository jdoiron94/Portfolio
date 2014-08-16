package volupnote.ui.view;

import javax.swing.text.Element;
import javax.swing.text.ParagraphView;

public class NoWrapView extends ParagraphView {

    public NoWrapView(Element element) {
        super(element);
    }

    @Override
    public void layout(int width, int height) {
        super.layout(Short.MAX_VALUE, height);
    }

    @Override
    public float getMinimumSpan(int axis) {
        return getPreferredSpan(axis);
    }
}