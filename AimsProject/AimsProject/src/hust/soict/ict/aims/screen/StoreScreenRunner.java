package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.exception.LimitExceededException;
import hust.soict.ict.aims.store.Store;
import hust.soict.ict.aims.media.*;

public class StoreScreenRunner {
    private static Store initStore() {
        Store store = new Store();


        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", 19.95f, "Roger Allers", 87));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", 24.95f, "George Lucas", 121));
        store.addMedia(new DigitalVideoDisc("Aladin", "Animation", 18.99f, "John Musker", 90));
        store.addMedia(new DigitalVideoDisc("Harry Potter", "Fantasy", 21.50f, "Chris Columbus", 152));


        Book book1 = new Book("The Alchemist", "Fantasy", 15.00f);
        book1.addAuthor("Paulo Coelho");
        store.addMedia(book1);

        Book book2 = new Book("1984", "Dystopian", 12.00f);
        book2.addAuthor("George Orwell");
        store.addMedia(book2);

        CompactDisc cd1 = new CompactDisc("Adele-30", "Pop", "Adele", 19.99f);
        store.addMedia(cd1);

        CompactDisc cd2 = new CompactDisc("Taylor-Swift-Midnights", "Pop", "Taylor Swift", 22.99f);
        store.addMedia(cd2);

        return store;
    }


    public static void main(String[] args) throws LimitExceededException {

        Store store = initStore();
        Cart cart = new Cart();


        new StoreScreen(store, cart);
    }
}

