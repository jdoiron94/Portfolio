package semester_ii.media_tracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Playlist {

    private final File FILE = new File("Playlist.ini");

    private final Set<Song> songs = new HashSet<>();
    private final Set<Video> videos = new HashSet<>();
    private final Set<Podcast> podcasts = new HashSet<>();

    public Set<Song> getSongs() {
        return songs;
    }

    public Set<Video> getVideos() {
        return videos;
    }

    public Set<Podcast> getPodcasts() {
        return podcasts;
    }

    public String getPath() {
        return FILE.getAbsolutePath();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public void removeVideo(Video video) {
        videos.remove(video);
    }

    public void addPodcast(Podcast podcast) {
        podcasts.add(podcast);
    }

    public void removePodcast(Podcast podcast) {
        podcasts.remove(podcast);
    }

    // Below does not work, will allow addition of duplicates later on.  Would need to use object serialization.

	/*public void imp() {
        if (FILE.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(FILE));
				int counter = -1;
				String line;
				outer: while ((line = reader.readLine()) != null) {
					if (line.contains("Songs:") || line.contains("Videos:") || line.contains("Podcasts:")) {
						counter++;
						continue;
					}
					if (!line.equals("")) {
						String[] split = line.split("\t");
						switch (counter) {
							case 0:
								Song song = new Song(split[0], split[1], split[2], split[3], split[4], Integer.parseInt(split[5]));
								if (!songs.contains(song)) {
									songs.add(song);
									System.out.println("Imported song: " + split[0]);
								}
								break;
							case 1:
								Video video = new Video(split[0], split[1], split[2]);
								if (!videos.contains(video)) {
									videos.add(video);
									System.out.println("Imported video: " + split[0]);
								}
								break;
							case 2:
								Podcast podcast = new Podcast(split[0], split[1], split[2], split[3]);
								if (!podcasts.contains(podcast)) {
									podcasts.add(podcast);
									System.out.println("Imported podcast: " + split[0]);
								}
								break;
							default:
								break outer;
						}
					}
				}
				reader.close();
			} catch (Exception ignored) {
				System.err.println("Lolwups, error importing.");
			}
		}
	}*/

    public void exp() {
        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
            } catch (IOException ignored) {
                System.err.println("Lolwups, error exporting.");
            }
        }
        try {
            FileWriter writer = new FileWriter(FILE);
            writer.write("Songs:" + System.lineSeparator() + System.lineSeparator());
            for (Song song : songs) {
                writer.write(song.getName() + "\t" + song.getArtist() + "\t" + song.getAlbum() + "\t" + song.getGenre() + "\t" + song.getLength() + "\t" + song.getBitsize() + System.lineSeparator());
            }
            writer.write(System.lineSeparator() + System.lineSeparator() + "Videos:" + System.lineSeparator() + System.lineSeparator());
            for (Video video : videos) {
                writer.write(video.getName() + "\t" + video.getProducer() + "\t" + video.getLength() + System.lineSeparator());
            }
            writer.write(System.lineSeparator() + System.lineSeparator() + "Podcasts:" + System.lineSeparator() + System.lineSeparator());
            for (Podcast podcast : podcasts) {
                writer.write(podcast.getName() + "\t" + podcast.getAuthor() + "\t" + podcast.getSite() + "\t" + podcast.getLength() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception ignored) {
            System.err.println("Lolwups, error exporting.");
        }
    }

    @Override
    public String toString() {
        String result = "Song playlist:\n\n";
        for (Song song : songs) {
            result += song + "\n\n";
        }
        result += "\nVideo playlist:\n\n";
        for (Video video : videos) {
            result += video + "\n\n";
        }
        result += "\nPodcast playlist:\n\n";
        for (Podcast podcast : podcasts) {
            result += podcast + "\n\n";
        }
        return result;
    }
}