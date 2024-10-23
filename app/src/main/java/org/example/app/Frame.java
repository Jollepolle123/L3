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
        this.setSize(1000, 800);
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
        saveFileName = new JTextField(8);
        saveFileName.setBounds(0, 0, 150, 10);

        String[] choices = {"png", "jpeg"};

        JLabel saveFileTypeLabel = new JLabel("File type:");
        saveFileType = new JComboBox<String>(choices);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.black);
        buttonPanel.add(uploadBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(saveFileName);
        buttonPanel.add(saveFileTypeLabel);
        buttonPanel.add(saveFileType);

        editButtonsPanel.setBackground(Color.black);
        JLabel widthLabel = new JLabel("Width:");
        width = new JTextField(8);
        width.setBounds(0, 0, 150, 100);
        editButtonsPanel.add(widthLabel);
        editButtonsPanel.add(width);
        JLabel heightLabel = new JLabel("Height:");
        height = new JTextField(8);
        height.setBounds(0, 0, 150, 100);
        editButtonsPanel.add(heightLabel);
        editButtonsPanel.add(height);
        editButtonsPanel.add(resizeBtn);
        editButtonsPanel.add(rotateBtn);
        editButtonsPanel.add(brightnessBtn);
        editButtonsPanel.add(colorFilterBtn);
        editButtonsPanel.add(invertColorBtn);

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
            editorManager.performAction(new RotateAction(20));
        } else if (source == brightnessBtn) {
            editorManager.performAction(new BrightnessAction(1));
        } else if (source == colorFilterBtn) {
            editorManager.performAction(new ColorFilterAction(1, 0, 1));
        } else if (source == invertColorBtn) {
            editorManager.performAction(new InvertColorAction());
        }
    }
}
