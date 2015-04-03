package file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import util.MiscUtil;

/**
 *
 * @author Daniel Plaza
 */
public abstract class MessageLogger {
  
    private static final String SEPARATOR=" ---> ";
    
    private PrintStream ps = null; 
    
    protected MessageLogger(File logFile,boolean append){
        if(logFile!=null){
            try {
                ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(logFile, append)), append);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Will print through console
     */
    public MessageLogger(){
        ps = new PrintStream((OutputStream)(System.out));
    }
    
    public MessageLogger(String logFile,boolean append){
        this(new File(logFile),append);
    }
    
    public MessageLogger(File logFile){
        this(logFile,true);
    }
    
    public MessageLogger(String logFile){
        this(new File(logFile),true);
    }
    
    protected void writeToLog(String line){
        if(ps!=null){
            ps.println(MiscUtil.DEFAULTDATEFORMAT.format(new Date())+SEPARATOR+line);
        }  
    }
}
