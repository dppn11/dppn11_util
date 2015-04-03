/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 *
 * @author Daniel Plaza
 */
    
public enum MiscUtil {;

    public static final SimpleDateFormat DEFAULTDATEFORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");    
        
    /**
     * It opens a URL in the user's default browser.
     *
     * @param url link to the web page
     * @throws java.net.MalformedURLException
     * @throws java.net.URISyntaxException
     */
    public static void openWebpage(String url) throws MalformedURLException, URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URL(url).toURI());
    }
    
    /**
     * @return Screen width
     */
    public static double getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    
    /**
     * @return Screen height
     */
    public static double getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
    
    /**
     * Copy to clipboard
     * @param text String to be copied to the clipboard
     */
    public static void copyToClipboard(String text){
        Toolkit.getDefaultToolkit ().getSystemClipboard ().setContents (new StringSelection (text), null);
    }
    
}
