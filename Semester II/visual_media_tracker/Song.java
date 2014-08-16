package semester_ii.visual_media_tracker;

public class Song extends Media {

    private final String artist;
    private final String album;
    private final String genre;

    public Song(String name, String artist, String album, String genre) {
        super(name);
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return getName() + " - " + artist + " - " + album + " (" + genre + ")";
    }
}