import javax.swing.*;

public class ImageInPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image");
        frame.setSize(500,500);
        frame.setVisible(true);
//        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("indianflag.png");
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.add(new JLabel(icon));
//        frame.setResizable(false);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("indianflag.png"));
        frame.add(label);

    }
}
