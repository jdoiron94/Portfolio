package semester_ii.media_tracker;

public class Video extends Media {

    private final String producer;

    public Video(String name, String producer, String length) {
        super(name, length);
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    @Override
    public Type getType() {
        return Type.VIDEO;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nProducer: %s\nLength: %s", getName(), producer, getLength());
    }
}