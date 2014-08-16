package volupnote.ui.fontselector;

import java.awt.Font;
import java.util.Comparator;

public class FontComparator implements Comparator<Font> {

    @Override
    public int compare(final Font f1, final Font f2) {
        return f1.getFontName().compareTo(f2.getFontName());
    }
}