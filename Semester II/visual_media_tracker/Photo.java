package semester_ii.visual_media_tracker;

public class Photo extends Media {

    private final String subject;

    public Photo(String name, String subject) {
        super(name);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return getName() + ": " + subject;
    }
}