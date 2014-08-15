package semester_ii.media_tracker;

public class Application {

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        Song s1 = new Song("Cynical", "Mr. FijiWiji, CoMa", "Aftermath", "Dubstep", "4:00", 320);
        System.out.println("Song:\n\n" + s1 + "\n");
        Video v1 = new Video("Harry Potter", "J.K. Rowling", "3:00:00");
        System.out.println("Video:\n\n" + v1 + "\n");
        Podcast p1 = new Podcast("How-to Podcast", "Howcast", "www.howcast.com", "3:00");
        System.out.println("Podcast:\n\n" + p1 + "\n");
        playlist.addSong(s1);
        playlist.addVideo(v1);
        playlist.addPodcast(p1);
        Song s2 = new Song("Chaos Storm", "Droptek", "Aftermath", "Dubstep", "4:15", 320);
        Video v2 = new Video("Ted", "Seth Macfarlane", "1:46:00");
        Podcast p2 = new Podcast("NPR", "NPR Podcast", "www.npr.org", "5:00");
        playlist.addSong(s2);
        playlist.addVideo(v2);
        playlist.addPodcast(p2);
        System.out.println(playlist);
        playlist.exp();
        System.out.println("Playlist exported to: " + playlist.getPath() + "\n");
        playlist.removeSong(s1);
        playlist.removeVideo(v1);
        playlist.removePodcast(p1);
        System.out.println(playlist);
    }
}