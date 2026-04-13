public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;

    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc){
        if (qtyOrdered == MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is full");
        }else {
            itemsOrdered[qtyOrdered] = disc;
            System.out.println("The disc \"" + disc.getTitle() + "\" has been added.");
            qtyOrdered++;
        }
    }
    public void addDigitalVideoDisc(DigitalVideoDisc [] dvdList){
        for (DigitalVideoDisc disc : dvdList){
            addDigitalVideoDisc(disc);
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc){
        boolean checkdisc = false;
        for (int i = 0; i <= qtyOrdered; i++) {
            if(itemsOrdered[i] == disc) {
                checkdisc = true;
                for (int j = i;j <= qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                    qtyOrdered--;
                    System.out.println("The disc \"" + disc.getTitle() + "\" has been removed from cart");
                }
                break;
            }
        }
        if (!checkdisc) {
            System.out.println("The disc is not found in the cart");
        }
    }

    public float totalCost(){
        float total = 0.0f;
        for (int i = 0; i < qtyOrdered; i++){
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }
    public DigitalVideoDisc[] getItemsOrdered() {
        return itemsOrdered;
    }
}