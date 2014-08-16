package volupnote.ui.tabs;

import volupnote.ui.panels.SourceContainer;

import javax.swing.Icon;

public class VTab {

    private String name;
    private String path;
    private final Icon icon;

    private final SourceContainer editor = new SourceContainer();

    public VTab(final String name, final String path, final Icon icon) {
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Icon getIcon() {
        return icon;
    }

    public SourceContainer getEditor() {
        return editor;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof VTab && name.equals(((VTab) object).getName()) && path.equals(((VTab) object).getPath());
    }
}