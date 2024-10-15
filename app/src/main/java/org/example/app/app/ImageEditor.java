package app;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageEditor {
    private BufferedImage image;

    // Läser in bilden som skickas in.
    public ImageEditor(String filePath) throws IOException {
        try {
            if (filePath.length() > 0) {
                this.image = ImageIO.read(new File(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sparar bilden till satt format och "path".
    public void saveImage(String imageFormat, String outPutPath) throws IOException {
        ImageIO.write(image, imageFormat, new File(outPutPath));
    }

    // Returnerar den aktuella bilden
    public BufferedImage getImage() {
        return image;
    }

    // Sätter en ny bild.
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}