import javax.swing.JPanel;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image image;


    public void setImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        this.image = icon.getImage();
        repaint();
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        //ImageIcon icon = new ImageIcon(imagePath);
        //image = icon.getImage();

        if (image != null) {
            int x = (getWidth() - image.getWidth(null)) / 2;
            int y = (getHeight() - image.getHeight(null)) / 2;
            g.drawImage(image, x, y, this);
        }
    }
}
