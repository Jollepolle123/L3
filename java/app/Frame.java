import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;  
import javax.swing.*;  
import javax.swing.filechooser.*;  
import java.io.File;

public class Frame extends JFrame implements ActionListener{

    JButton uploadBtn;

    Frame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setTitle("Image editor");
        this.getContentPane().setBackground(new Color(0,0,0));

        uploadBtn = new JButton("Upload File");
        uploadBtn.setFocusable(false);
        uploadBtn.addActionListener(this);
        uploadBtn.setSize(40, 40);
        JPanel panel = new JPanel();
        panel.add(uploadBtn);
        panel.setBackground(new Color(0,0,0));
        this.setContentPane(panel);
        //this.add(uploadBtn);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == uploadBtn) {
            JFileChooser file_upload = new JFileChooser();
            //file_upload.setCurrentDirectory(new File(""));

            int res = file_upload.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file_path = new File(file_upload.getSelectedFile().getAbsolutePath());
                System.out.println(file_path);
            }
        }
    }

}