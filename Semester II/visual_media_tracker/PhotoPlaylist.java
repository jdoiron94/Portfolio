package semester_ii.visual_media_tracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PhotoPlaylist {

    private final File file = new File("Photo Playlist.txt");
    private final Set<Photo> photos;

    public PhotoPlaylist() {
        photos = new HashSet<>(5);
    }

    public void addPhoto(Photo p) {
        photos.add(p);
    }

    public void removePhoto(Photo p) {
        photos.remove(p);
    }

    public void export() {
        try {
            FileWriter writer = new FileWriter(file);
            for (Photo photo : photos) {
                writer.write(photo + "\n");
            }
            writer.close();
            System.out.println("Photo playlist saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error exporting photo playlist");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(250);
        builder.append("Photos:\n");
        for (Photo photo : photos) {
            builder.append(photo);
            builder.append('\n');
        }
        return builder.toString();
    }
}