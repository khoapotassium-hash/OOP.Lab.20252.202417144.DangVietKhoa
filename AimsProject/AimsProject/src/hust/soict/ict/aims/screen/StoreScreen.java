package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.exception.LimitExceededException;
import hust.soict.ict.aims.media.Book;
import hust.soict.ict.aims.media.DigitalVideoDisc;
import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.store.Store;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public StoreScreen(Store store) {
        this.store = store;
        this.cart = new Cart();
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //North: Menu + Header
    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            try {
                new AddBookToStoreScreen(store, cart);
            } catch (LimitExceededException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });
        smUpdateStore.add(addBook);

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            try {
                new AddCompactDiscToStoreScreen(store, cart);
            } catch (LimitExceededException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });
        smUpdateStore.add(addCD);

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            try {
                new AddDigitalVideoDiscToStoreScreen(store, cart);
            } catch (LimitExceededException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });
        smUpdateStore.add(addDVD);
        
        menu.add(smUpdateStore);

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            new StoreScreen(store, cart);
            this.dispose();
        });
        menu.add(viewStore);

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(e -> {
            new CartScreen(store, cart);
            this.dispose();
        });
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        
        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 50));
        title.setForeground(Color.CYAN);

        JButton btnViewCart = new JButton("View cart");
        btnViewCart.setPreferredSize(new Dimension(100, 50));
        btnViewCart.setMaximumSize(new Dimension(100, 50));
        btnViewCart.addActionListener(e -> {
            new CartScreen(store, this.cart);
            this.dispose();
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(btnViewCart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    //Center
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
            center.add(cell);
        }
        return center;
    }

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();
        
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f, 87, "Roger Allers"));
        store.addMedia(new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f, 124, "George Lucas"));
        store.addMedia(new DigitalVideoDisc(3, "Aladdin", "Animation", 18.99f, 90, "John Musker"));
        store.addMedia(new Book(4, "The Lord of the Rings", "Fantasy", 25.50f));
        
        new StoreScreen(store, cart);
    }
}

