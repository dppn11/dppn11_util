
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Daniel Plaza
 */
public abstract class ConsoleIO {
    private static final BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Reads the next input line of the console
     * @return String with the input text
     */
    public static String readLine(){
        try {
            return bufferRead.readLine();
        } catch (IOException ex) {
            return null;
        } 
    }
    
    /**
     * Prints some text through console
     * @param line text to be printed
     */
    public static void print(Object line){
        System.out.print(line.toString());
    }
    
    /**
     * Prints some text and terminates the line
     * @param line text line to be printed
     */
    public static void println(Object line){
        System.out.println(line.toString());
    }
    
    /**
     * Terminates the current line
     */
    public static void println(){
        System.out.println();
    }
    
    /**
     * Prints a formated line
     * @param format format for the text line
     * @param args arguments of the line
     */
    public static void printf(String format, Object... args){
        System.out.printf(format, args);
    }
    
    /**
     * Prints an error
     * @param line error line text
     */
    public static void printerr(Object line){
        System.err.println(line);
    }
    
}
