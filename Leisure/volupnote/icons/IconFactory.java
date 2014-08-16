package volupnote.icons;

import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;

public class IconFactory {

    private final Map<String, ImageIcon> icons = new HashMap<>();

    public IconFactory() {
        final String[] IMAGE_NAMES = {"VolupNote", "Font Selector", "Apply", "Cancel", "Refresh"};
        for (final String image : IMAGE_NAMES) {
            icons.put(image, new ImageIcon(getClass().getResource("../resources/images/" + image + ".png")));
            System.out.println(image);
        }
    }

    public ImageIcon loadIcon(final String name) {
        return icons.get(name);
    }
}