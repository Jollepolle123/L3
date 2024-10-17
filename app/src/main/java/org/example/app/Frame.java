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
import app.ImageRotator;
import app.ImageBrightnessAdjuster;
import app.ImageColorFilter;
import java.awt.image.BufferedImage;

public class Frame extends JFrame implements ActionListener{

    private JButton uploadBtn;
    private JButton saveBtn;
    private JButton resizeBtn;
    private JButton rotateBtn;
    private JButton brightnessBtn;
    private JButton colorFilterBtn;
    private ImageEditor editor;
    private JPanel mainPanel = new JPanel();
    private JPanel editButtonsPanel = new JPanel();
    private JTextField height;
    private JTextField width;
    private JTextField rotation;
    private JTextField brightness;
    private JTextField red;
    private JTextField green;
    private JTextField blue;
    private ImagePanel imagePanel = new ImagePanel();
    private Image image;


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

        rotateBtn = new JButton("Rotate Image");
        rotateBtn.setFocusable(false);
        rotateBtn.addActionListener(this);
        rotateBtn.setSize(40, 40);

        JLabel rotateLabel = new JLabel("Rotation to the right (use negative numbers to rotate to the left):");
        rotation = new JTextField(8);
        rotation.setBounds(0, 0, 150, 10);

        brightnessBtn = new JButton("Adjust brightness");
        brightnessBtn.setFocusable(false);
        brightnessBtn.addActionListener(this);
        brightnessBtn.setSize(40, 40);

        JLabel brightnessLabel = new JLabel("Brightness multiplier (1 is current brightness):");
        brightness = new JTextField(8);
        brightness.setBounds(0, 0, 150, 10);

        colorFilterBtn = new JButton("Change colors");
        colorFilterBtn.setFocusable(false);
        colorFilterBtn.addActionListener(this);
        colorFilterBtn.setSize(40, 40);

        JLabel redLabel = new JLabel("Multiplier to the red colors in the picture:");
        red = new JTextField(8);
        red.setBounds(0, 0, 150, 10);

        JLabel greenLabel = new JLabel("Multiplier to the green colors in the picture:");
        green = new JTextField(8);
        green.setBounds(0, 0, 150, 10);

        JLabel blueLabel = new JLabel("Multiplier to the blue colors in the picture:");
        blue = new JTextField(8);
        blue.setBounds(0, 0, 150, 10);

        editButtonsPanel.setLayout(new GridLayout(4, 7, 10, 10));

        editButtonsPanel.add(resizeBtn);
        editButtonsPanel.add(label1);
        editButtonsPanel.add(height);
        editButtonsPanel.add(label2);
        editButtonsPanel.add(width);

        editButtonsPanel.add(rotateBtn);
        editButtonsPanel.add(rotateLabel);
        editButtonsPanel.add(rotation);

        editButtonsPanel.add(brightnessBtn);
        editButtonsPanel.add(brightnessLabel);
        editButtonsPanel.add(brightness);

        editButtonsPanel.add(colorFilterBtn);
        editButtonsPanel.add(redLabel);
        editButtonsPanel.add(red);
        editButtonsPanel.add(greenLabel);
        editButtonsPanel.add(green);
        editButtonsPanel.add(blueLabel);
        editButtonsPanel.add(blue);

        
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
                    editor.setImage(ImageIO.read(new File(file_path.getAbsolutePath())));
                    imagePanel.setImage(file_path.getAbsolutePath());
                    mainPanel.add(imagePanel, BorderLayout.CENTER);
                    mainPanel.add(editButtonsPanel, BorderLayout.CENTER);
                    this.setVisible(true);
                    //showImage(file_path.getAbsolutePath());
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
                ImageResizer resizer = new ImageResizer();
                BufferedImage resizedImage = resizer.resizeImage(editor.getImage(), newWidth, newHeight);
                editor.setImage(resizedImage);
            } catch (NumberFormatException e) {
            }
        } else if (event.getSource() == rotateBtn) {
            int newRotation;
            try {
                newRotation = Integer.parseInt(rotation.getText());
                ImageRotator rotator = new ImageRotator();
                BufferedImage rotatedImage = rotator.rotateImage(editor.getImage(), newRotation);
                editor.setImage(rotatedImage);
            } catch (NumberFormatException e) {
            }
        } else if (event.getSource() == brightnessBtn) {
            float brightnessMultiplier;
            try {
                brightnessMultiplier = Float.parseFloat(brightness.getText());
                ImageBrightnessAdjuster brightnessAdjuster = new ImageBrightnessAdjuster();
                BufferedImage brightenedImage = brightnessAdjuster.adjustBrightness(editor.getImage(), brightnessMultiplier);
                editor.setImage(brightenedImage);
            } catch (NumberFormatException e) {
            }
        } else if (event.getSource() == colorFilterBtn) {
            Float redMultiplier;
            Float greenMultiplier;
            Float blueMultiplier;
            try {
                redMultiplier = Float.parseFloat(red.getText());
                greenMultiplier = Float.parseFloat(green.getText());
                blueMultiplier = Float.parseFloat(blue.getText());
                ImageColorFilter colorFilter = new ImageColorFilter();
                BufferedImage filteredImage = colorFilter.adjustColor(editor.getImage(), redMultiplier, greenMultiplier, blueMultiplier);
                editor.setImage(filteredImage);
            } catch (NumberFormatException e) {
            }
        }
    }
}