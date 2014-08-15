package semester_ii.visual_media_tracker;

public class Photo extends Media {

    private String subject;

    public Photo(String n, String s) {
        super(n);
        subject = s;
    }

    public String getSubject() {
        return subject;
    }

    public String toString() {
        return getName() + ": " + subject;
    }
}