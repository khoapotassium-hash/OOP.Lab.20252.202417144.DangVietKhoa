package GUIProject.hust.soict.globalict.swing;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers;
    private JButton btnReset, btnDelete;
    private JTextField tfDisplay;

    public NumberGrid() {
        // 1. Tạo ô hiển thị chữ (Nằm ở phía trên)
        tfDisplay = new JTextField();
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); // Viết số từ phải qua trái

        // 2. Tạo một JPanel (Container cấp 2) chứa các nút bấm dạng lưới 4x3
        JPanel panelButtons = new JPanel(new GridLayout(4, 3));
        addButtons(panelButtons);

        // 3. Đưa tất cả vào JFrame bằng BorderLayout
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(tfDisplay, BorderLayout.NORTH);  // Ô chữ nằm ở phương Bắc (trên cùng)
        cp.add(panelButtons, BorderLayout.CENTER); // Bàn phím nằm ở Trung tâm

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(200, 200);
        setVisible(true);
    }

    // Hàm phụ trợ để thêm nút bấm vào Panel
    public void addButtons(JPanel panelButtons) {
        ButtonListener btnListener = new ButtonListener();
        
        // Thêm các nút từ 1 đến 9
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton("" + i);
            panelButtons.add(btn);
            btn.addActionListener(btnListener);
        }

        // Thêm nút DEL
        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(btnListener);

        // Thêm nút 0
        JButton btn0 = new JButton("0");
        panelButtons.add(btn0);
        btn0.addActionListener(btnListener);

        // Thêm nút C (Clear)
        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(btnListener);
    }

    // Class lắng nghe sự kiện bấm nút
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();
            
            if (button.charAt(0) >= '0' && button.charAt(0) <= '9') {
                // Nếu bấm số: Viết tiếp số đó vào màn hình
                tfDisplay.setText(tfDisplay.getText() + button);
            } else if (button.equals("DEL")) {
                // Nếu bấm DEL: Xóa ký tự cuối cùng
                String text = tfDisplay.getText();
                if(text.length() > 0) {
                    tfDisplay.setText(text.substring(0, text.length() - 1));
                }
            } else {
                // Nếu bấm C: Xóa trắng màn hình
                tfDisplay.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new NumberGrid();
    }
}

