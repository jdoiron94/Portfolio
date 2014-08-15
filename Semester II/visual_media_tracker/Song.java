package semester_ii.visual_media_tracker;

public class Song extends Media {

    private String artist;
    private String album;
    private String genre;

    public Song(String n, String art, String alb, String g) {
        super(n);
        artist = art;
        album = alb;
        genre = g;
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

    public String toString() {
        return getName() + " - " + artist + " - " + album + " (" + genre + ")";
    }
}