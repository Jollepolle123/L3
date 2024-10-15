import java.io.IOException;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;  
import javax.swing.*;  
import javax.swing.filechooser.*;  
import java.io.File;
import app.ImageEditor;
import javax.imageio.ImageIO;

public class Frame extends JFrame implements ActionListener{

    JButton uploadBtn;
    JButton saveBtn;
    ImageEditor editor;

    Frame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setTitle("Image editor");
        this.getContentPane().setBackground(new Color(0,0,0));

        uploadBtn = new JButton("Upload File");
        uploadBtn.setFocusable(false);
        uploadBtn.addActionListener(this);
        uploadBtn.setSize(40, 40);

        saveBtn = new JButton("Save Image");
        saveBtn.setFocusable(false);
        saveBtn.addActionListener(this);
        saveBtn.setSize(40, 40);

        JPanel panel = new JPanel();
        panel.add(uploadBtn);
        panel.add(saveBtn);
        panel.setBackground(new Color(0,0,0));

        this.setContentPane(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == uploadBtn) {
            JFileChooser file_upload = new JFileChooser();
            //file_upload.setCurrentDirectory(new File(""));
            
            int res = file_upload.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                try {
                    editor = new ImageEditor("");
                    File file_path = new File(file_upload.getSelectedFile().getAbsolutePath());
                    System.out.println(file_path);
                    //ImageEditor editor = new ImageEditor(file_path.getAbsolutePath());
                    editor.setImage(ImageIO.read(new File(file_path.getAbsolutePath())));
                    //editor.saveImage("png", file_path.getParent() + "/shalle1.png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (event.getSource() == saveBtn) {
            JFileChooser saveFolder = new JFileChooser();
            saveFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            saveFolder.setAcceptAllFileFilterUsed(false);

            if (saveFolder.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
                try {
                    String imageType = "png";
                    System.out.println("getSelectedFile() : " +  saveFolder.getSelectedFile().getAbsolutePath());
                    editor.saveImage(imageType, saveFolder.getSelectedFile().getAbsolutePath() + "/test." + imageType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}