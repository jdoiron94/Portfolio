package volupnote.ui.view;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.LabelView;
import javax.swing.text.View;

public class WrapLabelView extends LabelView {

    public WrapLabelView(final Element element) {
        super(element);
    }

    @Override
    public int getBreakWeight(final int axis, final float pos, final float len) {
        if (axis == View.X_AXIS) {
            checkPainter();
            final int p0 = getStartOffset();
            final int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len);
            if (p1 == p0) {
                return View.BadBreakWeight;
            }
            try {
                if (getDocument().getText(p0, p1 - p0).contains("\\r")) {
                    return View.ForcedBreakWeight;
                }
            } catch (final BadLocationException ignored) {}
        }
        return super.getBreakWeight(axis, pos, len);
    }

    @Override
    public View breakView(final int axis, final int p0, final float pos, final float len) {
        if (axis == View.X_AXIS) {
            checkPainter();
            final int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len);
            try {
                final int index = getDocument().getText(p0, p1 - p0).indexOf("\\r");
                if (index >= 0) {
                    return createFragment(p0, p0 + index + 1);
                }
            }
            catch (final BadLocationException ignored) {}
        }
        return super.breakView(axis, p0, pos, len);
    }
}