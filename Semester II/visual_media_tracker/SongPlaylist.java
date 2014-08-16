package semester_ii.visual_media_tracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SongPlaylist {

    private final File file = new File("Song Playlist.txt");
    private final Set<Song> songs;

    public SongPlaylist() {
        songs = new HashSet<>(5);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void export() {
        try {
            FileWriter writer = new FileWriter(file);
            for (Song song : songs) {
                writer.write(song + "\n");
            }
            writer.close();
            System.out.println("Song playlist saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error exporting song playlist");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(500);
        builder.append("Songs:\n");
        for (Song song : songs) {
            builder.append(song);
            builder.append('\n');
        }
        return builder.toString();
    }
}