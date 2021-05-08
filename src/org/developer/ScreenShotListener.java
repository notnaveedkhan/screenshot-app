package org.developer;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.developer.views.MainView;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * @author notnaveedkhan
 */
public class ScreenShotListener implements NativeKeyListener {

    boolean active = false;
    
    public boolean isActive() {
        return active;
    }
    
    public void start() {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(ScreenShotListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        GlobalScreen.addNativeKeyListener(this);
        active = true;
    }
    
    public void stop() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(ScreenShotListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        GlobalScreen.removeNativeKeyListener(this);
        active = false;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        if (nke.getKeyCode() == 3639) {
            MainView.screenshot();
        }
    }

}
