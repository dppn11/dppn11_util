
package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 *
 * @author Daniel Plaza
 */
public abstract class FileUtil {
    
    /**
     * Copy a file to a destination
     * @param source File to be copied
     * @param dest destination path
     * @throws IOException 
     */
    public static void copyFile(File source, File dest)throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }
    
    /**
     * Find out into a directory in order to get all the files with the string
     * passed as parameter.
     * 
     * @param path path to search in
     * @param ext extension of the desired files. Ex: ".jpg"
     * @return an array list of filenames
     */
    public static ArrayList<String> findFiles(String path,String ext) {
        ArrayList<String> fileList=new ArrayList<>();
        File f = new File(path);
        String[] allFiles = f.list();
        for (String file : allFiles) {
            if (file.contains(ext)) {
                fileList.add(file.substring(0, file.lastIndexOf(ext)));
            }
        }
        return fileList;
    }
    
    /**
     * Set the default output to a file rather than console
     * @param file text file
     * @throws FileNotFoundException 
     */
    public static void setOutFile(File file) throws FileNotFoundException {
        PrintStream ps=new PrintStream(file);
        System.setOut(ps);
    }
}
