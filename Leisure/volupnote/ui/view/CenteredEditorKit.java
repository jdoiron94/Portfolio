package volupnote.ui.view;

import javax.swing.text.*;

public class CenteredEditorKit extends StyledEditorKit {

    @Override
    public ViewFactory getViewFactory() {
        return new StyledViewFactory();
    }

    private class StyledViewFactory implements ViewFactory {

        @Override
        public View create(Element element) {
            String kind = element.getName();
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