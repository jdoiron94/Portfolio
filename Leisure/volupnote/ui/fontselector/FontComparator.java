package volupnote.ui.fontselector;

import java.awt.*;
import java.util.Comparator;

public class FontComparator implements Comparator<Font> {

    @Override
    public int compare(Font f1, Font f2) {
        return f1.getFontName().compareTo(f2.getFontName());
    }
}