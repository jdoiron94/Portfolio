package semester_ii.media_tracker;

public class Song extends Media {

    private final int bitsize;

    private final String artist;
    private final String album;
    private final String genre;

    public Song(int bitsize, String name, String artist, String album, String genre, String length) {
        super(name, length);
        this.bitsize = bitsize;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public int getBitsize() {
        return bitsize;
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
    public Type getType() {
        return Type.SONG;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nArtist: %s\nAlbum: %s\nGenre: %s\nLength: %s\nBitsize: %d", getName(), artist, album, genre, getLength(), bitsize);
    }
}