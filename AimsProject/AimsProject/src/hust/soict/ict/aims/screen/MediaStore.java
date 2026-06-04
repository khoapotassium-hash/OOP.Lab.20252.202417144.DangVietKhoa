package hust.soict.ict.aims.screen;

import java.awt.*;
import javax.swing.*;

import hust.soict.ict.aims.cart.Cart;
import hust.soict.ict.aims.media.Media;
import hust.soict.ict.aims.media.Playable;

public class MediaStore extends JPanel {
    public MediaStore(Media media, Cart cart) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(e -> {
            cart.addMedia(media);
            JOptionPane.showMessageDialog(this, media.getTitle() + " has been added to the cart.");
        });
        container.add(btnAddToCart);

        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(e -> {
                try {
                    ((Playable) media).play();
                    JOptionPane.showMessageDialog(this, "Playing: " + media.getTitle(),
                            "Playback Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Playback Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            container.add(btnPlay);
        }
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}