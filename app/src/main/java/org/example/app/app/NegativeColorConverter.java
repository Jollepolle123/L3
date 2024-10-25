package app;

import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 

public class NegativeColorConverter { 
    public BufferedImage convertColors (BufferedImage image) {

        int width = image.getWidth(); 
        int height = image.getHeight(); 
        
        // Tar alla pixlar från bilden och inverterar färgen genom att ta 255 - det rgb värdet som pixeln redan har.
        for (int y = 0; y < height; y++) { 
            for (int x = 0; x < width; x++) { 
                int p = image.getRGB(x, y); 

                // Förskjuter pixelns argb värde åt höger och tar de sista 8 bitarna för att få ut rätt värde för varje färg.
                int a = (p >> 24) & 0xff; 
                int r = (p >> 16) & 0xff; 
                int g = (p >> 8) & 0xff; 
                int b = p & 0xff; 
                
                r = 255 - r; 
                g = 255 - g; 
                b = 255 - b; 
                
                p = (a << 24) | (r << 16) | (g << 8) | b; 
                image.setRGB(x, y, p); 
            } 
        } 
        return image;
    }
}
