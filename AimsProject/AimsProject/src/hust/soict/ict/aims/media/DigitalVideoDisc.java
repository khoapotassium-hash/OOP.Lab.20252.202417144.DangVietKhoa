package hust.soict.ict.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost, length, director);
    }

    public void play() {
        if (this.getLength() > 0) {
            System.out.println("Playing: " + this.getTitle());
            System.out.println("Length: " + this.getLength() + " minutes");
        } else {
            System.out.println("This media cannot be played.");
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