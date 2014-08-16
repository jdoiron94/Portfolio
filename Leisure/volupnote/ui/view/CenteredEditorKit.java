package volupnote.ui.view;

import javax.swing.text.AbstractDocument;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class CenteredEditorKit extends StyledEditorKit {

    @Override
    public ViewFactory getViewFactory() {
        return new StyledViewFactory();
    }

    private class StyledViewFactory implements ViewFactory {

        @Override
        public View create(final Element element) {
            final String kind = element.getName();
            if (kind != null) {
                switch (kind) {
                    case AbstractDocument.ContentElementName:
                        return new ParagraphView(element);
                    case AbstractDocument.SectionElementName:
                        return new CenteredView(element, View.Y_AXIS);
                    case StyleConstants.ComponentElementName:
                        return new ComponentView(element);
                    case StyleConstants.IconElementName:
                        return new IconView(element);
                }
            }
            return new LabelView(element);
        }
    }
}