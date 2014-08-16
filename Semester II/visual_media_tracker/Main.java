package semester_ii.visual_media_tracker;

public class Main {

    public static void main(String... args) {
        Song song1 = new Song("Song1 Name", "Song1 Artist", "Song1 Album", "Song1 Genre");
        Video video1 = new Video("Video1 Name", "Video1 Director");
        Photo photo1 = new Photo("Photo1 Name", "Photo1 Subject");
        SongPlaylist sPlaylist = new SongPlaylist();
        sPlaylist.addSong(song1);
        System.out.println(sPlaylist);
        sPlaylist.addSong(new Song("Song2 Name", "Song2 Artist", "Song2 Album", "Song2 Genre"));
        sPlaylist.removeSong(song1);
        System.out.println(sPlaylist);
        sPlaylist.addSong(new Song("Song3 Name", "Song3 Artist", "Song3 Album", "Song3 Genre"));
        System.out.println(sPlaylist);
        sPlaylist.export();
        VideoPlaylist vPlaylist = new VideoPlaylist();
        vPlaylist.addVideo(video1);
        System.out.println(vPlaylist);
        vPlaylist.addVideo(new Video("Video2 Name", "Video2 Director"));
        vPlaylist.removeVideo(video1);
        System.out.println(vPlaylist);
        vPlaylist.addVideo(new Video("Video3 Name", "Video3 Director"));
        System.out.println(vPlaylist);
        vPlaylist.export();
        PhotoPlaylist pPlaylist = new PhotoPlaylist();
        pPlaylist.addPhoto(photo1);
        System.out.println(pPlaylist);
        pPlaylist.addPhoto(new Photo("Photo2 Name", "Photo2 Subject"));
        pPlaylist.removePhoto(photo1);
        System.out.println(pPlaylist);
        pPlaylist.addPhoto(new Photo("Photo3 Name", "Photo3 Subject"));
        System.out.println(pPlaylist);
        pPlaylist.export();
    }
}