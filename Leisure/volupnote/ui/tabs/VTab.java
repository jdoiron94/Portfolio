package volupnote.ui.tabs;

import volupnote.ui.panels.SourceContainer;

import javax.swing.*;

public class VTab {

    private String name;
    private String path;
    private final Icon icon;
    private final SourceContainer editor = new SourceContainer();

    public VTab(String name, String path, Icon icon) {
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Icon getIcon() {
        return icon;
    }

    public SourceContainer getEditor() {
        return editor;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof VTab && name.equals(((VTab) object).name) && path.equals(((VTab) object).path);
    }
}