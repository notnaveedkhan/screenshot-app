package org.developer;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @author notnaveedkhan
 */
public class CustomJFrame extends JFrame {

    public CustomJFrame() throws HeadlessException {
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Screen Shot Application");
        BufferedImage image;
        try {
            image = ImageIO.read(new File("icon.png"));
            setIconImage(image);
        } catch (IOException ex) {
            Logger.getLogger(CustomJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
