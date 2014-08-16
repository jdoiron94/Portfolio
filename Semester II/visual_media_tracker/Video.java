package semester_ii.visual_media_tracker;

public class Video extends Media {

    private final String director;

    public Video(String name, String director) {
        super(name);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return getName() + " - " + director;
    }
}