package hust.soict.ict.aims.media;

import hust.soict.ict.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost, 0, "");
    }

    public DigitalVideoDisc(String title, String category, float cost, String director, int length) {
        super(title, category, cost, length, director);
    }

    public DigitalVideoDisc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost, length, director);
    }

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing: " + this.getTitle());
            System.out.println("Length: " + this.getLength() + " minutes");
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    public boolean isMatch(String title) {
        return this.getTitle().toLowerCase().contains(title.toLowerCase());
    }

    @Override
    public String toString() {
        return "DVD - " 
        + getTitle() + " - " 
        + getCategory() + " - " 
        + getDirector() + " - " 
        + getLength() 
        + ": " 
        + getCost() + " $";
    }
}

