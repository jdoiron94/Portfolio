package volupnote.ui.fontselector;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FontLoader {

    //private final Font[] programmingFonts = new Font[2];

    public FontLoader() {
        //try {
        //    programmingFonts[0] = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/Inconsolata.ttf"));
        //    programmingFonts[1] = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/Deja Vu Sans Mono.ttf"));
        loadFonts();
        System.out.println("Set programming fonts");
        //} catch (FontFormatException | IOException ignored) {
        //    System.err.println("Unable to set fonts");
        //}
    }

    /*private Set<Font> registerFonts(GraphicsEnvironment environment, Set<Font> fonts) {
        for (Font font : programmingFonts) {
            if (!fonts.contains(font)) {
                System.out.println("Registering font: " + font.getFontName());
                environment.registerFont(font);
                fonts.add(font);
            }
        }
        FontVars.setCurrentFont(programmingFonts[0].deriveFont(Font.PLAIN, 15));
        return fonts;
    }*/

    public Set<Font> loadFonts() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (environment != null) {
            Set<Font> fonts = new TreeSet<>(new FontComparator());
            outer:
            for (Font font : environment.getAllFonts()) {
                for (char character : font.getFontName().toCharArray()) {
                    if (!font.canDisplay(character)) {
                        System.err.println("Skipping font: " + font.getFontName());
                        continue outer;
                    }
                }
                System.out.println("Adding font: " + font.getFontName());
                fonts.add(font);
            }
            return fonts;
            //return registerFonts(environment, fonts);
        }
        return new HashSet<>(150);
    }
}