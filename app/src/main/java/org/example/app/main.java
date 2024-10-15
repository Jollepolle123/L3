import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import app.ImageEditor;
 
public class main {
    public static void main(String args[]) {

        try {

            ImageEditor editor = new ImageEditor("");
            //editor.saveImage("png", "images/hardy1.png");

            Frame frame = new Frame();
    
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}