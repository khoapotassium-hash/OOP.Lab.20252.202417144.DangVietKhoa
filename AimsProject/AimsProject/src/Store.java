public class Store {
    public static final int MAX_ITEMS_IN_STORE = 1000;

    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    public void addDVD(DigitalVideoDisc dvd) {
        if (qtyInStore < MAX_ITEMS_IN_STORE) {
            itemsInStore[qtyInStore] = dvd;
            qtyInStore++;
            System.out.println("Store added DVD \"" + dvd.getTitle() + "\" .");
        } else {
            System.out.println("Store is full, cannot add new DVD!");
        }
    }

    public void removeDVD(DigitalVideoDisc dvd) {
        boolean found = false;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] == dvd) { 
                found = true;
                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[qtyInStore - 1] = null;
                qtyInStore--;
                System.out.println("Store removed DVD \"" + dvd.getTitle() + "\" .");
                break;
            }
        }
        if (!found) {
            System.out.println("DVD \"" + dvd.getTitle() + "\" not found in the store!");
        }
    }
}