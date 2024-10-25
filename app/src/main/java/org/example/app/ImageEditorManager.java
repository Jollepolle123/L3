import app.ImageEditor;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.*;
import java.io.File;

public class ImageEditorManager {

    private ImageEditor editor;
    private ImagePanel imagePanel;

    public ImageEditorManager(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public boolean uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int res = fileChooser.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                editor = new ImageEditor(file.getAbsolutePath());
                editor.setImage(ImageIO.read(file));
                imagePanel.setImage(file.getAbsolutePath());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void saveImage(JFrame frame, String fileName, String imageType) {
        JFileChooser saveFolder = new JFileChooser();
        saveFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        saveFolder.setAcceptAllFileFilterUsed(false);

        if (saveFolder.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) { 
            try {
                System.out.println("getSelectedFile() : " +  saveFolder.getSelectedFile().getAbsolutePath() + "/" + fileName + "." + imageType);
                editor.saveImage(imageType, saveFolder.getSelectedFile().getAbsolutePath() + "/" + fileName + "." + imageType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void performAction(ImageAction action) {
        action.execute(editor);
        imagePanel.updateImage(editor.getImage());
    }
}
