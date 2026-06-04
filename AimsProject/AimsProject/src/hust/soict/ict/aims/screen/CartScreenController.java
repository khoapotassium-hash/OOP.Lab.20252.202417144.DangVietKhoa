package hust.soict.ict.aims.screen;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.exception.LimitExceededException;
import hust.soict.ict.aims.exception.PlayerException;
import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.media.Playable;
import hust.soict.ict.aims.store.Store;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javax.swing.JFrame;

public class CartScreenController {
    private Cart cart;
    private Store store;
    private JFrame containingFrame; // Dùng để đóng JFrame mẹ
    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private ToggleGroup filterCategory;
    @FXML private Label lblTotalCost;

    private FilteredList<Media> filteredData;

    public CartScreenController(Store store, Cart cart, JFrame containingFrame) {
        super();
        this.store = store;
        this.cart = cart;
        this.containingFrame = containingFrame;
    }

    public CartScreenController(Store store, Cart cart) {
        this(store, cart, null);
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));

        filteredData = new FilteredList<>(this.cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredData);
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        } else {
                            btnPlay.setVisible(false);
                            btnRemove.setVisible(false);
                        }
                    }
                }
        );

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });
        cart.getItemsOrdered().addListener(new ListChangeListener<Media>() {
            @Override
            public void onChanged(Change<? extends Media> c) {
                Platform.runLater(() -> {
                    updateTotalCost();
                    tblMedia.refresh();
                });
            }
        });

        updateTotalCost();
    }
    private void updateTotalCost() {
        if (lblTotalCost != null && cart != null) {
            lblTotalCost.setText(String.format("%.2f $", cart.totalCost()));
        }
    }
    private void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }
    private void showFilteredMedia(String keyword) {
        String newKeyword = keyword.toLowerCase();
        filteredData.setPredicate(media -> {
            if (newKeyword == null || newKeyword.isEmpty()) {
                return true;
            }
            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(newKeyword);
            } else {
                return media.getTitle().toLowerCase().contains(newKeyword);
            }
        });
    }

    @FXML
    private void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Notification");
        alert.setHeaderText(null);
        alert.setContentText("Order created successfully. Total: " + String.format("%.2f $", cart.totalCost()));
        alert.showAndWait();

        cart.getItemsOrdered().clear();
        updateTotalCost();
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) throws PlayerException {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null && media instanceof Playable) {
            ((Playable) media).play();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Playing Media");
            alert.setHeaderText("Playing: " + media.getTitle());
            alert.setContentText("Media is now playing...");
            alert.showAndWait();
        }
    }
    private void disposeCurrentScreen(ActionEvent event) {
        if (containingFrame != null) {
            containingFrame.dispose();
        } else {
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        }
    }
    @FXML
    void mnuAddDVDClicked(ActionEvent event) throws LimitExceededException {
        new AddDigitalVideoDiscToStoreScreen(this.store, this.cart);
        disposeCurrentScreen(event);
    }

    @FXML
    void mnuAddCDClicked(ActionEvent event) throws LimitExceededException {
        new AddCompactDiscToStoreScreen(this.store, this.cart);
        disposeCurrentScreen(event);
    }

    @FXML
    void mnuAddBookClicked(ActionEvent event) throws LimitExceededException {
        new AddBookToStoreScreen(this.store, this.cart);
        disposeCurrentScreen(event);
    }
    @FXML
    void mnuViewStoreClicked(ActionEvent event) {
            new StoreScreen(this.store, this.cart);
        disposeCurrentScreen(event);
    }
    @FXML
    void mnuViewCartClicked(ActionEvent event) {
        updateTotalCost();
        tblMedia.refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NOTIFICATION");
        alert.setHeaderText(null);
        alert.setContentText("THE CART IS RENEWED");
        alert.showAndWait();
    }
}

