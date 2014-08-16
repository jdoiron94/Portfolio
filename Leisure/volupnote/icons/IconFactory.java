package volupnote.icons;

import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;

public class IconFactory {

    private final Map<String, ImageIcon> icons = new HashMap<>(5);

    public IconFactory() {
        for (String image : new String[]{"VolupNote", "Font Selector", "Apply", "Cancel", "Refresh"}) {
            icons.put(image, new ImageIcon(getClass().getResource("../resources/images/" + image + ".png")));
            System.out.println(image);
        }
    }

    public ImageIcon loadIcon(String name) {
        return icons.get(name);
    }
}