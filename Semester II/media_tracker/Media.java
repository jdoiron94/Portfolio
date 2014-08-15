package semester_ii.media_tracker;

public abstract class Media {

    private final String name;
    private final String length;

    public Media(String name, String length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public String getLength() {
        return length;
    }

    public abstract Type getType();

    public enum Type {
        SONG,
        VIDEO,
        PODCAST
    }
}