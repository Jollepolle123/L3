import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private Image image;

    ImagePanel() {
        this.setBackground(Color.black);
    }

    public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImage(BufferedImage newImage) {
        this.image = newImage;
        repaint();
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int x = (getWidth() - image.getWidth(null)) / 2;
            int y = (getHeight() - image.getHeight(null)) / 2;
            g.drawImage(image, x, y, this);
        }
    }
}
