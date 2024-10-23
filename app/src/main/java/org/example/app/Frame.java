import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {

    private JButton uploadBtn, saveBtn, resizeBtn, rotateBtn, brightnessBtn, colorFilterBtn, invertColorBtn;
    private JTextField height, width, rotation, brightness, red, green, blue, saveFileName;
    private JComboBox<String> saveFileType;
    private ImageEditorManager editorManager;
    private JPanel mainPanel = new JPanel();
    private ImagePanel imagePanel = new ImagePanel();
    private JPanel editButtonsPanel = new JPanel();

    public Frame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setTitle("Image editor");

        initializeComponents();
        editorManager = new ImageEditorManager(imagePanel);

        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    private void initializeComponents() {
        uploadBtn = createButton("Upload File", this);
        saveBtn = createButton("Save Image", this);

        resizeBtn = createButton("Resize Image", this);
        rotateBtn = createButton("Rotate Image", this);
        brightnessBtn = createButton("Adjust Brightness", this);
        colorFilterBtn = createButton("Change Colors", this);
        invertColorBtn = createButton("Invert Colors", this);

        JLabel saveFileNameLabel = new JLabel("File name:");
        saveFileNameLabel.setForeground(Color.WHITE);
        saveFileName = new JTextField(15);

        String[] choices = {"png", "jpeg"};

        JLabel saveFileTypeLabel = new JLabel("File type:");
        saveFileTypeLabel.setForeground(Color.WHITE);
        saveFileType = new JComboBox<String>(choices);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonPanel.setBackground(Color.black);
        buttonPanel.add(uploadBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(saveFileNameLabel);
        buttonPanel.add(saveFileName);
        buttonPanel.add(saveFileTypeLabel);
        buttonPanel.add(saveFileType);

        editButtonsPanel.setBackground(Color.black);
        editButtonsPanel.setLayout(new GridLayout(3,2));
        editButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        JPanel resizePanel = new JPanel(new GridLayout(2,2));
        resizePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        resizePanel.setBackground(Color.black);

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setForeground(Color.WHITE);
        width = new JTextField(10);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setForeground(Color.WHITE);
        height = new JTextField(10);

        JPanel resizebtnPanel = new JPanel();
        resizebtnPanel.setBackground(Color.black);
        resizebtnPanel.add(resizeBtn);

        JPanel widthPanel = new JPanel();
        widthPanel.setLayout(new FlowLayout());
        widthPanel.setBackground(Color.black);
        widthPanel.add(widthLabel);
        widthPanel.add(width);

        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new FlowLayout());
        heightPanel.setBackground(Color.black);
        heightPanel.add(heightLabel);
        heightPanel.add(height);

        resizePanel.add(widthPanel);
        resizePanel.add(heightPanel);
        resizePanel.add(resizebtnPanel);

        editButtonsPanel.add(resizePanel);


        JPanel rotatePanel = new JPanel(new GridLayout(2,1));
        rotatePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rotatePanel.setBackground(Color.black);

        JLabel rotateLabel = new JLabel("Rotation:");
        rotateLabel.setForeground(Color.WHITE);
        rotation = new JTextField(10);

        JPanel rotatebtnPanel = new JPanel();
        rotatebtnPanel.setBackground(Color.black);
        rotatebtnPanel.add(rotateBtn);

        JPanel rotationPanel = new JPanel();
        rotationPanel.setLayout(new FlowLayout());
        rotationPanel.setBackground(Color.black);
        rotationPanel.add(rotateLabel);
        rotationPanel.add(rotation);

        rotatePanel.add(rotationPanel);
        rotatePanel.add(rotatebtnPanel);

        editButtonsPanel.add(rotatePanel);


        JPanel brightnessPanel = new JPanel(new GridLayout(2,1));
        brightnessPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        brightnessPanel.setBackground(Color.black);

        JLabel brightnessLabel = new JLabel("Brightness multiplier:");
        brightnessLabel.setForeground(Color.WHITE);
        brightness = new JTextField(10);

        JPanel brightnessbtnPanel = new JPanel();
        brightnessbtnPanel.setBackground(Color.black);
        brightnessbtnPanel.add(brightnessBtn);

        JPanel brightnessEditPanel = new JPanel();
        brightnessEditPanel.setLayout(new FlowLayout());
        brightnessEditPanel.setBackground(Color.black);
        brightnessEditPanel.add(brightnessLabel);
        brightnessEditPanel.add(brightness);

        brightnessPanel.add(brightnessEditPanel);
        brightnessPanel.add(brightnessbtnPanel);

        editButtonsPanel.add(brightnessPanel);


        JPanel colorFilterPanel = new JPanel(new GridLayout(2,2));
        colorFilterPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        colorFilterPanel.setBackground(Color.black);

        JLabel redLabel = new JLabel("Red multiplier:");
        redLabel.setForeground(Color.WHITE);
        red = new JTextField(10);

        JLabel greenLabel = new JLabel("Green multiplier:");
        greenLabel.setForeground(Color.WHITE);
        green = new JTextField(10);

        JLabel blueLabel = new JLabel("Blue multiplier:");
        blueLabel.setForeground(Color.WHITE);
        blue = new JTextField(10);

        JPanel colorFilterbtnPanel = new JPanel();
        colorFilterbtnPanel.setBackground(Color.black);
        colorFilterbtnPanel.add(colorFilterBtn);

        JPanel redPanel = new JPanel();
        redPanel.setLayout(new FlowLayout());
        redPanel.setBackground(Color.black);
        redPanel.add(redLabel);
        redPanel.add(red);

        JPanel greenPanel = new JPanel();
        greenPanel.setLayout(new FlowLayout());
        greenPanel.setBackground(Color.black);
        greenPanel.add(greenLabel);
        greenPanel.add(green);

        JPanel bluePanel = new JPanel();
        bluePanel.setLayout(new FlowLayout());
        bluePanel.setBackground(Color.black);
        bluePanel.add(blueLabel);
        bluePanel.add(blue);

        colorFilterPanel.add(redPanel);
        colorFilterPanel.add(greenPanel);
        colorFilterPanel.add(bluePanel);
        colorFilterPanel.add(colorFilterbtnPanel);

        editButtonsPanel.add(colorFilterPanel);

        JPanel invertColorPanel = new JPanel();
        invertColorPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        invertColorPanel.setBackground(Color.black);

        invertColorPanel.add(invertColorBtn);

        editButtonsPanel.add(invertColorPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
        mainPanel.add(imagePanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == uploadBtn) {
            editorManager.uploadImage();
            mainPanel.add(editButtonsPanel, BorderLayout.SOUTH);
            this.setVisible(true);
        } else if (source == saveBtn) {
            editorManager.saveImage(this, saveFileName.getText(), (String) saveFileType.getSelectedItem());
        } 
        else if (source == resizeBtn) {
            editorManager.performAction(new ResizeAction(Integer.parseInt(width.getText()), Integer.parseInt(height.getText())));
        }  else if (source == rotateBtn) {
            editorManager.performAction(new RotateAction(Integer.parseInt(rotation.getText())));
        } else if (source == brightnessBtn) {
            editorManager.performAction(new BrightnessAction(Integer.parseInt(brightness.getText())));
        } else if (source == colorFilterBtn) {
            editorManager.performAction(new ColorFilterAction(1, 0, 1));
        } else if (source == invertColorBtn) {
            editorManager.performAction(new InvertColorAction());
        }
    }
}
