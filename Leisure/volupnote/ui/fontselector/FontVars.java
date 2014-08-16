package volupnote.ui.fontselector;

import javax.swing.DefaultListModel;
import java.awt.Font;
import java.util.Set;

public class FontVars {

    protected static Font currentFont;
    protected static Set<Font> fonts;
    protected static DefaultListModel<String> fontModel;

    public static Font getCurrentFont() {
        return currentFont;
    }

    public static void setCurrentFont(Font currentFont) {
        FontVars.currentFont = currentFont;
    }

    public static Set<Font> getFonts() {
        return fonts;
    }

    public static void setFonts(Set<Font> fonts) {
        FontVars.fonts = fonts;
    }

    public static DefaultListModel<String> getFontModel() {
        return fontModel;
    }

    public static void setFontModel(DefaultListModel<String> fontModel) {
        FontVars.fontModel = fontModel;
    }
}