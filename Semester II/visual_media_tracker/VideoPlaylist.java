package semester_ii.visual_media_tracker;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class VideoPlaylist {

    private File file = new File("Video Playlist.txt");

    private Set<Video> videos;

    public VideoPlaylist() {
        videos = new HashSet<>();
    }

    public void addVideo(Video v) {
        videos.add(v);
    }

    public void removeVideo(Video v) {
        videos.remove(v);
    }

    public void export() {
        try {
            FileWriter writer = new FileWriter(file);
            for (Video video : videos) {
                writer.write(video.toString() + "\n");
            }
            writer.close();
            System.out.println("Video playlist saved to " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error exporting video playlist");
            e.printStackTrace();
        }
    }

    public String toString() {
        String s = "Videos:\n";
        for (Video video : videos) {
            s = s + video.toString() + "\n";
        }
        return s;
    }
}