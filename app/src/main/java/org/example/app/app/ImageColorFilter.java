package app;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageColorFilter {

    // Justerar rgb värderna på bilden.
    public BufferedImage adjustColor(BufferedImage image, float red, float green, float blue) {
        BufferedImage brightenedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = clamp((int) (color.getRed() * red));
                int g = clamp((int) (color.getGreen() * green));
                int b = clamp((int) (color.getBlue() * blue));
                Color newColor = new Color(r, g, b);
                brightenedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return brightenedImage;
    }

    private int clamp(int value) {
        if (value > 255){
            return 255;
        } else if (value < 0) {
            return 0;
        } else {
            return value;
        }
    }
}