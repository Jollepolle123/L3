package app;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageBrightnessAdjuster {
    private ValidColorCheck colorChecker = new ValidColorCheck();

    // Justerar ljusstyrkan på bilden
    public BufferedImage adjustBrightness(BufferedImage image, float brightnessFactor) {
        BufferedImage brightenedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        //Loopar igenom alla pixlar på bilden och multiplicerar deras rgb värden med det värdet som skickats in.
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = colorChecker.clamp((int) (color.getRed() * brightnessFactor));
                int g = colorChecker.clamp((int) (color.getGreen() * brightnessFactor));
                int b = colorChecker.clamp((int) (color.getBlue() * brightnessFactor));
                Color newColor = new Color(r, g, b);
                brightenedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return brightenedImage;
    }
}