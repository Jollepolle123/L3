package app;

public class ValidColorCheck {

    // Metod för att se till att RGB-värden är inom giltigt intervall (0-255)
    public int clamp(int value) {
        if (value > 255){
            return 255;
        } else if (value < 0) {
            return 0;
        } else {
            return value;
        }
    }
}