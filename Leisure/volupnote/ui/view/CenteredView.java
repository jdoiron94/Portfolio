package volupnote.ui.view;

import javax.swing.text.BoxView;
import javax.swing.text.Element;

public class CenteredView extends BoxView {

    public CenteredView(final Element element, final int axis) {
        super(element, axis);
    }

    @Override
    protected void layoutMajorAxis(final int targetSpan, final int axis, final int[] offsets, final int[] spans) {
        super.layoutMajorAxis(targetSpan, axis, offsets, spans);
        final int offset = (targetSpan - spans[spans.length - 1]) / 2;
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] += offset;
        }
    }
}