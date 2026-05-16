package hust.soict.ict.aims;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.*;
import hust.soict.ict.aims.store.Store;
import java.util.Scanner;

public class Aims { 
    private static int idCounter = 0;

    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeStore(); 
        
        while (true) {
            try {
                showMenu(); 
                int choice = scanner.nextInt();
                scanner.nextLine();
                handleMainMenu(choice);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public static void initializeStore() {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(++idCounter, "The Lion King", "Animation", 19.95f, 87, "Roger Allers");
        store.addMedia(dvd1);
        
        Book book1 = new Book(++idCounter, "The Lord of the Rings", "Fantasy", 25.50f);
        book1.addAuthor("J.R.R. Tolkien");
        store.addMedia(book1);
        
        CompactDisc cd1 = new CompactDisc(++idCounter, "My Way", "Pop", 15.00f, 44, "Don Costa", "Frank Sinatra");
        cd1.addTrack(new Track("All of Nothing at All", 3));
        cd1.addTrack(new Track("I've Got You Under My Skin", 5));
        cd1.addTrack(new Track("Try a Little Tenderness", 5));
        cd1.addTrack(new Track("The Only Thing I Care About",3));
        cd1.addTrack(new Track("I Will Drink the Wine", 5));
        cd1.addTrack(new Track("Forget Domani", 5));
        cd1.addTrack(new Track("Mrs. Robinson", 3));
        cd1.addTrack(new Track("By the Time I get to Phoenix", 6));
        cd1.addTrack(new Track("Didn't we",4));
        cd1.addTrack(new Track("My Way", 5));
        store.addMedia(cd1);
        
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(++idCounter, "Aladdin", "Animation", 18.99f, 90, "Ron Clements");
        store.addMedia(dvd2);
        
        Book book2 = new Book(++idCounter, "The Communist Manifesto", "Philosophy", 5.99f);
        book2.addAuthor("Karl Marx");
        store.addMedia(book2);

        Book book3 = new Book(++idCounter, "My Way", "Autobiography", 6.50f);
        book3.addAuthor("Me");
        book3.addAuthor("Frank Sinatra");
        store.addMedia(book3);
    }

    public static void showMenu() { 
        System.out.println("\n--- AIMS: An Internet Media Store ---");
        System.out.println("-------------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
        System.out.print("Please choose a number (0-1-2-3): ");
    }

    public static void storeMenu() { 
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-1-2-3-4): ");
    }

    public static void mediaDetailsMenu(Media media) { 
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        if (media instanceof Playable) {
            System.out.println("2. Play");
        }
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
    }

   public static void cartMenu() { 
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number (0-1-2-3-4-5): ");
    }

    public static void handleMainMenu(int choice) {
        switch (choice) {
            case 1: viewStore(); break;
            case 2: updateStore(); break;
            case 3: seeCart(); break;
            case 0:
                System.out.println("Thank you for using AIMS. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice.");
        }
    }   

    public static void viewStore() {
        store.print(); 
        
        while (true) {
            try {
                storeMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                if (choice == 0) return; 
                handleStoreMenu(choice);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }   
    
    public static void handleStoreMenu(int choice) {
        int id;
        Media foundMedia = null;     
        try {
            switch (choice) {
                case 1: 
                    System.out.print("Enter Media ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    foundMedia = store.fetchMediaById(id);
                    if (foundMedia != null) {
                        System.out.println("Media Details:\n" + foundMedia.toString());
                        handleMediaDetailsMenu(foundMedia); 
                    } else {
                        System.out.println("The media is not found.");
                    }
                    break;
                case 2: 
                    System.out.print("Enter Media ID to add: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    foundMedia = store.fetchMediaById(id);
                    if (foundMedia != null) {
                        cart.addMedia(foundMedia);
                    } else {
                        System.out.println("The media is not found.");
                    }
                    break;
                case 3: 
                    System.out.print("Enter Media ID to Play: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    foundMedia = store.fetchMediaById(id);
                    if (foundMedia != null) {
                        if (foundMedia instanceof Playable) {
                            ((Playable) foundMedia).play();
                        } else {
                            System.out.println("This media is not playable.");
                        }
                    } else {
                        System.out.println("The media is not found.");
                    }
                    break;
                case 4: 
                    seeCart();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during processing: " + e.getMessage());
        }
    }   

    public static void handleMediaDetailsMenu(Media media) {
        mediaDetailsMenu(media);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: 
                cart.addMedia(media);
                break;
            case 2: 
                if (media instanceof Playable) {
                    ((Playable) media).play();
                } else {
                    System.out.println("This media is not playable.");
                }
                break;
            case 0: 
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void seeCart() {
        cart.print();
        
        while (true) {
            try {
                cartMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                if (choice == 0) return;
                handleCartMenu(choice);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public static void handleCartMenu(int choice) {
        try {
            switch (choice) {
                case 1: filterCart(); break;
                case 2: sortCart(); break; 
                case 3: removeMediaFromCart(); break;
                case 4: playMediaInCart(); break;
                case 5: placeOrder(); return;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing the cart: " + e.getMessage());
        }
    }

    public static void updateStore() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Update Store ---");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number (0-1-2): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        System.out.println("--------------------------------");
                        System.out.println("1. Add a Book");
                        System.out.println("2. Add a DVD");
                        System.out.println("3. Add a CD");
                        System.out.println("0. Back");
                        System.out.println("--------------------------------");
                        System.out.print("Choose media type to add (0-1-2-3): ");
                        int updateType = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (updateType == 0) {
                            break;
                        }
                       
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter category: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter cost: ");
                        float cost = scanner.nextFloat();
                        scanner.nextLine();
                        
                        int id = ++idCounter;
                        
                        if (updateType == 1) {
                            Book newBook = new Book(id, title, category, cost);
                            System.out.print("Enter number of authors: ");
                            int numAuthors = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numAuthors; i++) {
                                System.out.print("Enter author " + (i + 1) + ": ");
                                String author = scanner.nextLine();
                                newBook.addAuthor(author);
                            }
                            store.addMedia(newBook);
                            System.out.println("The media \"" + newBook.getTitle() + "\" has been added to store successfully.");
                        } else if (updateType == 2) {
                            System.out.print("Enter director: ");
                            String dvdDirector = scanner.nextLine();
                            System.out.print("Enter length: ");
                            int dvdLength = scanner.nextInt();
                            scanner.nextLine();
                            DigitalVideoDisc newDVD = new DigitalVideoDisc(id, title, category, cost, dvdLength, dvdDirector);
                            store.addMedia(newDVD);
                            System.out.println("The media \"" + newDVD.getTitle() + "\" has been added to store successfully.");
                        } else if (updateType == 3) {
                            System.out.print("Enter director: ");
                            String cdDirector = scanner.nextLine();
                            System.out.print("Enter length: ");
                            int cdLength = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter artist: ");
                            String cdArtist = scanner.nextLine();
                            CompactDisc newCD = new CompactDisc(id, title, category, cost, cdLength, cdDirector, cdArtist);
                            
                            System.out.print("Enter number of tracks: ");
                            int numTracks = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numTracks; i++) {
                                System.out.println("Track " + (i + 1) + ":");
                                System.out.print("\tEnter track title: ");
                                String trackTitle = scanner.nextLine();
                                System.out.print("\tEnter track length: ");
                                int trackLength = scanner.nextInt();
                                scanner.nextLine();
                                newCD.addTrack(new Track(trackTitle, trackLength));
                            }
                            
                            store.addMedia(newCD);
                            System.out.println("The media \"" + newCD.getTitle() + "\" has been added to store successfully.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter media ID to remove: ");
                        int removeId = scanner.nextInt();
                        scanner.nextLine();
                        Media mediaToRemove = store.fetchMediaById(removeId);
                        if (mediaToRemove != null) {
                            store.removeMedia(mediaToRemove);
                            System.out.println("The media \"" + mediaToRemove.getTitle() + "\" has been removed from store successfully.");
                        } else {
                            System.out.println("The media is not found in the store.");
                        }
                        break;

                    case 0:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }  

    public static void filterCart() {
        System.out.println("\n--- Filter Options ---");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by Title");
        System.out.print("Choose filter method (1-2): ");
        int filterChoice = scanner.nextInt();
        scanner.nextLine();    
        switch (filterChoice) {
            case 1:
                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                cart.searchById(id);
                break;
            case 2:
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                cart.searchByTitle(title);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }   

    public static void sortCart() {
        System.out.println("\n--- Sorting Options ---");
        System.out.println("1. Sort by Title (then Cost)");
        System.out.println("2. Sort by Cost (then Title)");
        System.out.print("Please choose a number (1-2): ");        
        int sortChoice = scanner.nextInt();
        scanner.nextLine();      
        
        switch (sortChoice) {
            case 1:
                cart.sortByTitleCost();
                break;
            case 2:
                cart.sortByCostTitle();
                break;
            default:
                System.out.println("Invalid sorting option.");
        }   
        cart.print(); 
    }

    public static void removeMediaFromCart() {
        System.out.print("Enter Media ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Media mediaToRemove = cart.fetchMediaById(id);

        if (mediaToRemove != null) {
            cart.removeMedia(mediaToRemove);
            System.out.println("The media \"" + mediaToRemove.getTitle() + "\" has been removed from cart successfully.");
        } else {
            System.out.println("The media is not found in the cart.");
        }
    }

    public static void playMediaInCart() {
        System.out.print("Enter Media ID to Play: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Media foundMedia = cart.fetchMediaById(id);
        if (foundMedia != null) {
            if (foundMedia instanceof Playable) {
                ((Playable) foundMedia).play();
            } else {
                System.out.println("This media is not playable.");
            }
        } else {
            System.out.println("The media is not found in the cart.");
        }
    }

    public static void placeOrder() {
        if (cart.getQtyOrdered() == 0) {
            System.out.println("The cart is empty. Cannot place order.");
            return;
        }

        System.out.println("Total cost is " + cart.totalCost() + " $. Are you sure you want to place the order? (Y/N)");
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println("Order cancelled.");
            return;
        } else {              
        System.out.println("\n--- THE ORDER HAS BEEN CREATED ---");
        System.out.println("Total cost: " + cart.totalCost() + " $");
        System.out.println("The order has been successfully created! Thank you for shopping with us.");
        System.out.println("-----------------------------------");      
        cart.empty();
        }
    }
}