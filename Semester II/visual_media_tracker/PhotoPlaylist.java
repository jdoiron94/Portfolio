package semester_ii.visual_media_tracker;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class PhotoPlaylist {

    private File file = new File("Photo Playlist.txt");

    private Set<Photo> photos;

    public PhotoPlaylist() {
        photos = new HashSet<>();
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
                writer.write(photo.toString() + "\n");
            }
            writer.close();
            System.out.println("Photo playlist saved to " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error exporting photo playlist");
            e.printStackTrace();
        }
    }

    public String toString() {
        String s = "Photos:\n";
        for (Photo photo : photos) {
            s = s + photo.toString() + "\n";
        }
        return s;
    }
}