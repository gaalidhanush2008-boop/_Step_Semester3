import java.util.Arrays;

/**
 * Week 7 - Problem 2: The Playlist
 * getSongs() returns a copy, so callers can never change the real playlist.
 */
public class P2_Playlist {

    public static void main(String[] args) {
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");

        String[] copy = p.getSongs();
        copy[0] = "Hacked";               // only changes the copy

        System.out.println("Copy:     " + Arrays.toString(copy));       // [Hacked, Song B]
        System.out.println("Playlist: " + Arrays.toString(p.getSongs())); // [Song A, Song B]
        System.out.println("Count:    " + p.getSongCount());            // 2
    }
}

class Playlist {
    private final String[] songs;
    private int count;

    Playlist(int maxSize) {
        this.songs = new String[maxSize];
    }

    boolean addSong(String title) {
        if (count == songs.length) {
            return false;                 // playlist is full
        }
        songs[count++] = title;
        return true;
    }

    String[] getSongs() {
        return Arrays.copyOf(songs, count);   // a brand new array with the same contents
    }

    int getSongCount() {
        return count;
    }
}
