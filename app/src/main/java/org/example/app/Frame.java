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
import app.ImageResizer;
import java.awt.image.BufferedImage;

public class Frame extends JFrame implements ActionListener{

    private JButton uploadBtn;
    private JButton saveBtn;
    private JButton resizeBtn;
    private ImageEditor editor;
    private JPanel mainPanel = new JPanel();
    private JPanel editButtonsPanel = new JPanel();
    private JTextField height;
    private JTextField width;


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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(uploadBtn);
        buttonPanel.add(saveBtn);

        resizeBtn = new JButton("Resize Image");
        resizeBtn.setFocusable(false);
        resizeBtn.addActionListener(this);
        resizeBtn.setSize(40, 40);

        JLabel label1 = new JLabel("Height:");
        height = new JTextField(8);
        height.setBounds(0, 0, 150, 10);

        JLabel label2 = new JLabel("Width:");
        width = new JTextField(8);
        width.setBounds(0, 0, 150, 100);

        editButtonsPanel.add(resizeBtn);
        editButtonsPanel.add(label1);
        editButtonsPanel.add(height);
        editButtonsPanel.add(label2);
        editButtonsPanel.add(width);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        mainPanel.setBackground(new Color(0,0,0));

        this.setContentPane(mainPanel);
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
                    showImage(file_path.getAbsolutePath());
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
        } else if (event.getSource() == resizeBtn) {
            int newWidth;
            int newHeight;
            try {
                newWidth = Integer.parseInt(width.getText());
                newHeight = Integer.parseInt(height.getText());
            } catch (NumberFormatException e) {
                break;
            }
            ImageResizer resizer = new ImageResizer();
            BufferedImage resizedImage = resizer.resizeImage(editor.getImage(), newWidth, newHeight);
            editor.setImage(resizedImage);
        }
    }

    void showImage(String imagePath) {
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage();

                if (image != null) {
                    int x = (getWidth() - image.getWidth(null)) / 2;
                    int y = (getHeight() - image.getHeight(null)) / 2;
                    g.drawImage(image, x, y, this);
                }
            }
        };
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(editButtonsPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}