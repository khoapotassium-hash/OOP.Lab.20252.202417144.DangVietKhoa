package hust.soict.ict.aims.screen;

import javax.swing.*;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.exception.LimitExceededException;
import hust.soict.ict.aims.media.Book;
import hust.soict.ict.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store, Cart cart) throws LimitExceededException {
        super(store, cart);
        setTitle("Add Book to Store");
    }

    @Override
    protected void addSpecificFields(JPanel center) {
        center.add(new JLabel("Authors:"));
        tfAuthors = new JTextField();
        center.add(tfAuthors);
    }

    @Override
    protected void addItemToStore() {
        Book book = new Book();

        book.setTitle(tfTitle.getText());
        book.setCategory(tfCategory.getText());
        try {
            book.setCost(Float.parseFloat(tfCost.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cost must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String authorsText = tfAuthors.getText();
        if (authorsText != null && !authorsText.isEmpty()) {
            String[] authors = authorsText.split(",");
            for (String author : authors) {
                book.addAuthor(author.trim());
            }
        }

        store.addMedia(book);
        JOptionPane.showMessageDialog(this, "Book added successfully");

        tfTitle.setText("");
        tfCategory.setText("");
        tfCost.setText("");
        tfAuthors.setText("");
    }
}

