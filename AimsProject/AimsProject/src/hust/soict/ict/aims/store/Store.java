package hust.soict.ict.aims.store;

import hust.soict.ict.aims.media.Media;
import java.util.ArrayList;

public class Store {
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
    
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
        }
    }

    public void print() {
        System.out.println("\n***********************STORE***********************");
        System.out.println("Items in store:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println(itemsInStore.get(i).getId() + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("***************************************************");
    }

    public Media fetchMediaById(int id) {
        for (Media media : itemsInStore) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public Media fetchMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().trim().equalsIgnoreCase(title.trim())) {
                return media;
            }
        }
        return null;
    }
}