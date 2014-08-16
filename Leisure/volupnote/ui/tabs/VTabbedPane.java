package volupnote.ui.tabs;

import javax.swing.JTabbedPane;
import java.util.ArrayList;
import java.util.List;

public class VTabbedPane extends JTabbedPane {

    private final List<VTab> tabs = new ArrayList<>();

    public VTabbedPane(final VTab... tabs) {
        for (final VTab tab : tabs) {
            addTab(tab);
            this.tabs.add(tab);
        }
    }

    public List<VTab> getTabs() {
        return tabs;
    }

    public void addTab(final VTab tab) {
        for (final VTab t : tabs) {
            if (t.equals(tab)) {
                return;
            }
        }
        addTab(tab.getName(), tab.getIcon(), tab.getEditor(), tab.getPath());
        tabs.add(tab);
        setTabComponentAt(tabs.size() - 1, new VTabPanel(this, tab));
    }

    public void removeTab(final VTab tab) {
        final int index = tabs.indexOf(tab);
        if (index >= 0) {
            removeTabAt(index);
            tabs.remove(index);
        }
    }
}