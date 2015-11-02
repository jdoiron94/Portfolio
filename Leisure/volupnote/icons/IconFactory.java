package volupnote.icons;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IconFactory {

    private final Map<String, ImageIcon> icons = new HashMap<>(5);

    public IconFactory() {
        String[] iconNames = {"VolupNote", "Font Selector", "Apply", "Cancel", "Refresh"};
        Class clazz = getClass();
        for (String name : iconNames) {
            URL resource = clazz.getResource("../resources/images/" + name + ".png");
            ImageIcon icon = new ImageIcon(resource);
            icons.put(name, icon);
            System.out.println(name);
        }
    }

    public ImageIcon loadIcon(String name) {
        return icons.get(name);
    }
}