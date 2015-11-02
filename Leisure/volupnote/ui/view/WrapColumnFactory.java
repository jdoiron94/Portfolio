package volupnote.ui.view;

import javax.swing.text.*;

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