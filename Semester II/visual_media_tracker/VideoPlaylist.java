package semester_ii.visual_media_tracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class VideoPlaylist {

    private final File file = new File("Video Playlist.txt");
    private final Set<Video> videos;

    public VideoPlaylist() {
        videos = new HashSet<>(5);
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
                writer.write(video + "\n");
            }
            writer.close();
            System.out.println("Video playlist saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error exporting video playlist");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(250);
        builder.append("Videos:\n");
        for (Video video : videos) {
            builder.append(video);
            builder.append('\n');
        }
        return builder.toString();
    }
}