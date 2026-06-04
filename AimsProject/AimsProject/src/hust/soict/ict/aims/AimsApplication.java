package hust.soict.ict.aims;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.screen.CartScreenController;
import hust.soict.ict.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AimsApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Aims.initSetup();
        Store myStore = Aims.getStore();
        Cart myCart = Aims.getCart();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/hust/soict/ict/aims/screen/cart.fxml")
            );


            CartScreenController controller = new CartScreenController(myStore, myCart);
            fxmlLoader.setController(controller);

            Parent root = fxmlLoader.load();

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Cart - AIMS Application (JavaFX)");
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("--- ERROR LOADING FXML FATAL (AimsApplication) ---");
            e.printStackTrace();
            throw e;

        } catch (Exception e) {
            System.err.println("--- ERROR INITIALIZING CONTROLLER FATAL (AimsApplication) ---");
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}