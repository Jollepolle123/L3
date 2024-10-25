package app;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageColorFilter {
    private ValidColorCheck colorChecker = new ValidColorCheck();

    // Justerar rgb värderna på bilden.
    public BufferedImage adjustColor(BufferedImage image, float red, float green, float blue) {
        BufferedImage brightenedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        //Loopar igenom alla pixlar på bilden och multiplicerar deras rgb värden med dem värdena som skickats in.
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int r = colorChecker.clamp((int) (color.getRed() * red));
                int g = colorChecker.clamp((int) (color.getGreen() * green));
                int b = colorChecker.clamp((int) (color.getBlue() * blue));
                Color newColor = new Color(r, g, b);
                brightenedImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return brightenedImage;
    }
}