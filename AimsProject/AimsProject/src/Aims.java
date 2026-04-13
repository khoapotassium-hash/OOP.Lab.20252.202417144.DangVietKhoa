public class Aims {
    public static void main(String[] args){
        Cart anOder = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation","Roger Allers",87,19.95f);
        anOder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Scific","George Lucas",87,24.95f);
        anOder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation",18.99f);
        anOder.addDigitalVideoDisc(dvd3);

        System.out.print("Total cost is: ");
        System.out.println(anOder.totalCost());

        //Test removing
        System.out.println();
        anOder.removeDigitalVideoDisc(dvd3);
        anOder.removeDigitalVideoDisc(dvd3);

        System.out.print("Total cost is: ");
        System.out.println(anOder.totalCost());

    }
}