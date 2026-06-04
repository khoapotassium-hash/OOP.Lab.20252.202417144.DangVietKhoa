package hust.soict.ict.test.disc;
import hust.soict.ict.aims.media.DigitalVideoDisc;

public class TestingPassingParamter {
    public static void main (String[] args){
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc(1, "Jungle", "Animation", 19.95f, 87, "Unknown");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc(2, "Cinderella", "Animation", 18.99f, 87, "Unknown");

        Holder container = new Holder(jungleDVD, cinderellaDVD);
        swap(container);
        System.out.println("Jungle dvd title: " + ((DigitalVideoDisc)container.o1).getTitle());
        System.out.println("Cinderella dvd title: " + ((DigitalVideoDisc)container.o2).getTitle());

        changeTitle((DigitalVideoDisc)container.o1, ((DigitalVideoDisc)container.o2).getTitle());
        System.out.println("Jungle dvd title: " + ((DigitalVideoDisc)container.o1).getTitle());
    }
    
    static class Holder {
        Object o1;
        Object o2;
        
        Holder(Object o1, Object o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }
    
    public static void swap(Holder holder){
        Object tmp = holder.o1;
        holder.o1 = holder.o2;
        holder.o2 = tmp;
    }
    public static void changeTitle(DigitalVideoDisc dvd, String title){
        dvd.setTitle(title);
    }
}


