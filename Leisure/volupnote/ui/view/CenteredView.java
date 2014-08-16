package volupnote.ui.view;

import javax.swing.text.BoxView;
import javax.swing.text.Element;

public class CenteredView extends BoxView {

    public CenteredView(Element element, int axis) {
        super(element, axis);
    }

    @Override
    protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {
        super.layoutMajorAxis(targetSpan, axis, offsets, spans);
        int offset = (targetSpan - spans[spans.length - 1]) / 2;
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] += offset;
        }
    }
}