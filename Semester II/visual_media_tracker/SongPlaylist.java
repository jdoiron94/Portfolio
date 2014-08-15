package semester_ii.visual_media_tracker;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class SongPlaylist {

    private File file = new File("Song Playlist.txt");

    private Set<Song> songs;

    public SongPlaylist() {
        songs = new HashSet<>();
    }

    public void addSong(Song s) {
        songs.add(s);
    }

    public void removeSong(Song s) {
        songs.remove(s);
    }

    public void export() {
        try {
            FileWriter writer = new FileWriter(file);
            for (Song song : songs) {
                writer.write(song.toString() + "\n");
            }
            writer.close();
            System.out.println("Song playlist saved to " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error exporting song playlist");
            e.printStackTrace();
        }
    }

    public String toString() {
        String s = "Songs:\n";
        for (Song song : songs) {
            s = s + song.toString() + "\n";
        }
        return s;
    }
}