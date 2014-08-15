package semester_ii.media_tracker;

public class Podcast extends Media {

    private final String author;
    private final String site;

    public Podcast(String name, String author, String site, String length) {
        super(name, length);
        this.author = author;
        this.site = site;
    }

    public String getAuthor() {
        return author;
    }

    public String getSite() {
        return site;
    }

    @Override
    public Type getType() {
        return Type.PODCAST;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nAuthor: %s\nSite: %s\nLength: %s", getName(), author, site, getLength());
    }
}