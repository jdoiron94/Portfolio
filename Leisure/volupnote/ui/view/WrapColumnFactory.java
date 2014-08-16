package volupnote.ui.view;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class WrapColumnFactory implements ViewFactory {

    @Override
    public View create(Element element) {
        String kind = element.getName();
        if (kind != null) {
            switch (kind) {
                case AbstractDocument.ContentElementName:
                    return new WrapLabelView(element);
                case AbstractDocument.ParagraphElementName:
                    return new NoWrapView(element);
                case AbstractDocument.SectionElementName:
                    return new BoxView(element, View.Y_AXIS);
                case StyleConstants.ComponentElementName:
                    return new ComponentView(element);
                case StyleConstants.IconElementName:
                    return new IconView(element);
            }
        }
        return new LabelView(element);
    }
}