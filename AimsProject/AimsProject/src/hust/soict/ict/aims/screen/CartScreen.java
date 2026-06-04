package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.exception.LimitExceededException;
import hust.soict.ict.aims.media.DigitalVideoDisc;
import hust.soict.ict.aims.store.Store;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame {

    static {
        try {
            javafx.application.Platform.startup(() -> {});
        } catch (IllegalStateException e) {
        }
    }
    public CartScreen(Store store, Cart cart) {
        super();
        this.setLayout(new BorderLayout());
        this.setTitle("Cart Screen - JavaFX");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel, BorderLayout.CENTER);

        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                    CartScreenController controller = new CartScreenController(store, cart, CartScreen.this);
                    loader.setController(controller);

                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "ERROR FXML: CANNOT LOAD cart.fxml",
                            "ERROR INITIALIZING",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.setVisible(true);
    }
    public static void main(String[] args) throws LimitExceededException {
        Store store = new Store();
        Cart cart = new Cart();
        cart.addMedia(new DigitalVideoDisc("The Lion King", "Animation", 19.95f, "Roger Allers", 87));
        cart.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", 24.95f, "George Lucas", 87));
        store.addMedia(new DigitalVideoDisc("Aladin", "Animation", 18.99f));
        new CartScreen(store, cart);
    }
}