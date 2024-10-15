package app;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageBrightnessAdjuster {

    // Justerar ljusstyrkan på bilden
    public BufferedImage adjustBrightness(BufferedImage image, float brightnessFactor) {
        BufferedImage brightenedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = clamp((int) (color.getRed() * brightnessFactor));
                int g = clamp((int) (color.getGreen() * brightnessFactor));
                int b = clamp((int) (color.getBlue() * brightnessFactor));
                Color newColor = new Color(r, g, b);
                brightenedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return brightenedImage;
    }

    // Metod för att se till att RGB-värden är inom giltigt intervall (0-255)
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