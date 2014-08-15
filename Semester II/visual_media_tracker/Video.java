package semester_ii.visual_media_tracker;

public class Video extends Media {

    private String director;

    public Video(String n, String d) {
        super(n);
        director = d;
    }

    public String getDirector() {
        return director;
    }

    public String toString() {
        return getName() + " - " + director;
    }
}