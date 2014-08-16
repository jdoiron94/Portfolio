package volupnote;

import volupnote.icons.IconFactory;
import volupnote.ui.fontselector.FontLoader;

public class Context {

    public static final IconFactory factory = new IconFactory();

    protected static FontLoader fontLoader;

    protected static FontLoader getFontLoader() {
        return fontLoader;
    }

    public static void setFontLoader(FontLoader fontLoader) {
        Context.fontLoader = fontLoader;
    }
}