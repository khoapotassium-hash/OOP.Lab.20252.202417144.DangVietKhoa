import hust.soict.ict.aims.media.DigitalVideoDisc;

public class TestingPassingParamter {
    public static void main (String[] args){
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

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
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }
}
