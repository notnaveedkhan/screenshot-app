package org.developer;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.developer.views.MainView;

/**
 * @author notnaveedkhan
 */
public class ScreenShotApp {

    private static CustomJFrame frame;
    private static MainView mainView;

    private static void init() {
        frame = new CustomJFrame();
        mainView = new MainView();
        setTrayIcon();
    }

    public static void main(String[] args) {
        layout("Windows");
        init();
        setNewPanel(mainView);
        frame.setVisible(true);
    }

    public static void refreshFrame() {
        frame.revalidate();
        frame.setVisible(true);
        frame.pack();
    }

    public static void setNewPanel(MainView mainView) {
        frame.setContentPane(mainView);
        refreshFrame();
    }

    private static void layout(String layout) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (layout.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenShotApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private static void setTrayIcon() {
        if (!SystemTray.isSupported()) {
            JOptionPane.showMessageDialog(frame, "Try icon not supported.", "Tray icon error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        final PopupMenu popup = new PopupMenu();
        BufferedImage image;
        TrayIcon trayIcon = null;
        try {
            image = ImageIO.read(new File("icon.png"));
            trayIcon = new TrayIcon(image, "ScreenShot App");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Try icon could not found.", "Tray icon error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        final SystemTray tray = SystemTray.getSystemTray();
        MenuItem aboutItem = new MenuItem("About");
        MenuItem showItem = new MenuItem("Show");
        showItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                frame.setVisible(true);
            }
        });
        
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        
        popup.add(aboutItem);
        popup.add(showItem);
        popup.add(exitItem);
        trayIcon.setPopupMenu(popup);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(frame, "Tray icon failed to load.", "Tray icon error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
