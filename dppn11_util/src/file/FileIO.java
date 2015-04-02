package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 *
 * @author Daniel Plaza
 */
public class FileIO {

    private RandomAccessFile raf;
    private FileChannel channel;
    private FileLock lock = null;

    public FileIO(File file) {
        try {
            raf = new RandomAccessFile(file, "rw");
            channel = raf.getChannel();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public FileIO(String file) {
        this(new File(file));
    }
    
    /**
     * Read the next line of the file moving the pointer (offset) to the next 
     * line
     * @return String which contains the line following the pointer
     * @throws IOException if a I/O error occurs 
     */
    public String readLine() throws IOException {
        String line;
        lock();
        line = raf.readLine();
        unlock();
        return line;
    }
    
    /**
     * Write text in the file
     * @param text String to be written
     * @throws IOException if a I/O error occurs 
     */
    public void write(String text) throws IOException {
        lock();
        raf.writeBytes(text.replace("\n", "\r\n"));
        unlock();
    }
    
    /**
     * Similar to write.
     * Write a line and a line separator at the end of this
     * @param line line to be written
     * @throws IOException if a I/O error occurs
     */
    public void writeln(String line) throws IOException {
        write(line + "\n");
    }
    
    /**
     * Sets the file pointer offset
     * @param pos new position of the offset
     * @throws IOException if a I/O error occurs 
     */
    public void seek(int pos) throws IOException {
        raf.seek(pos);
    }
    
    /**
     * Return the current offset in this file
     * @return offset value
     * @throws IOException if a I/O error occurs
     */
    public long offset() throws IOException{
        return raf.getFilePointer();
    }
    
    /**
     * Returns the length of this file
     * @return the length of this file
     * @throws IOException if a I/O error occurs 
     */
    public long getLength() throws IOException {
        return raf.length();
    }

    private void lock() throws IOException {
        lock = channel.lock();
    }

    private void unlock() throws IOException {
        lock.release();
    }

}
