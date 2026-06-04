package hust.soict.ict.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc(String title, String category, float cost, int length, String director, String artist) {
        super(title, category, cost, length, director);
        this.artist = artist;
    }

    public CompactDisc(String title, String category, String artist, float cost) {
        super(title, category, cost, 0, "");
        this.artist = artist;
    }

    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
    }

    public String getArtist() { return artist; }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    public void play() {
        if (this.getLength() > 0) {
            System.out.println("Playing CD: " + this.getTitle() + " by " + artist);
            System.out.println("CD length: " + this.getLength());
            for (Track track : tracks) {
                track.play();
            }
        } else {
            System.out.println("CD cannot be played.");
        }
    }

    @Override
    public String toString() {
        return "CD - " 
        + getTitle() + " - " 
        + getCategory() + " - " 
        + getDirector() + " - " 
        + artist + " - " 
        + getLength() 
        + ": " 
        + getCost() + " $";
    }
}

