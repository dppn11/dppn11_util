/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author Daniel Plaza
 */
public class MiscUtil {
    
    /**
     * It opens a URL in the user's default browser.
     *
     * @param url link to the web page
     */
    public static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (IOException | URISyntaxException e) {
        }
    }
    
    /**
     * @return Screen width
     */
    public static double getDefaultWidth(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.getWidth();
    }
    
    /**
     * @return Screen height
     */
    public static double getDefaultHeight(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.getHeight();
    }
}
