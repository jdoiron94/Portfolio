package volupnote.ui.tabs;

import javax.swing.JTabbedPane;
import java.util.ArrayList;
import java.util.List;

public class VTabbedPane extends JTabbedPane {

    private final List<VTab> tabs = new ArrayList<>(5);

    public VTabbedPane(VTab... tabs) {
        for (VTab tab : tabs) {
            addTab(tab);
            this.tabs.add(tab);
        }
    }

    public List<VTab> getTabs() {
        return tabs;
    }

    public void addTab(VTab tab) {
        for (VTab t : tabs) {
            if (t.equals(tab)) {
                return;
            }
        }
        addTab(tab.getName(), tab.getIcon(), tab.getEditor(), tab.getPath());
        tabs.add(tab);
        setTabComponentAt(tabs.size() - 1, new VTabPanel(this, tab));
    }

    public void removeTab(VTab tab) {
        int index = tabs.indexOf(tab);
        if (index >= 0) {
            removeTabAt(index);
            tabs.remove(index);
        }
    }
}