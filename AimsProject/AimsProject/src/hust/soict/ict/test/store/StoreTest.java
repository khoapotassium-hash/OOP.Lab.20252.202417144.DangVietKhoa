package hust.soict.ict.test.store;
import hust.soict.ict.aims.media.DigitalVideoDisc;
import hust.soict.ict.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King",
                "Animation", 19.95f, 87, "Roger Allers");
        store.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars",
                "Scific", 24.95f, 87, "George Lucas");
        store.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladin",
                "Animation", 18.99f, 87, "John Musker");
        store.addMedia(dvd3);

        store.removeMedia(dvd3);
        store.removeMedia(dvd3);
    }
}