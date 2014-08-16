package volupnote.ui.fontselector;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FontLoader {

    private final Font[] programmingFonts = new Font[2];

    private Set<Font> loadedFonts = null;

    public FontLoader() {
        try {
            programmingFonts[0] = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/Inconsolata.ttf"));
            programmingFonts[1] = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/fonts/Deja Vu Sans Mono.ttf"));
            loadFonts();
            System.out.println("Set programming fonts");
        } catch (final FontFormatException | IOException ignored) {
            System.err.println("Unable to set fonts");
        }
    }

    private Set<Font> registerFonts(final GraphicsEnvironment environment, final Set<Font> fonts) {
        for (final Font font : programmingFonts) {
            if (!fonts.contains(font)) {
                System.out.println("Registering font: " + font.getFontName());
                environment.registerFont(font);
                fonts.add(font);
            }
        }
        FontVars.currentFont = programmingFonts[0].deriveFont(Font.PLAIN, 15);
        return fonts;
    }

    public Set<Font> loadFonts() {
        final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (environment != null) {
            final Set<Font> fonts = new TreeSet<>(new FontComparator());
            outer: for (final Font font : environment.getAllFonts()) {
                for (final char character : font.getFontName().toCharArray()) {
                    if (!font.canDisplay(character)) {
                        System.err.println("Skipping font: " + font.getFontName());
                        continue outer;
                    }
                }
                System.out.println("Adding font: " + font.getFontName());
                fonts.add(font);
            }
            return loadedFonts = registerFonts(environment, fonts);
        }
        return new HashSet<>();
    }
}