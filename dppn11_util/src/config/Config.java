package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import file.FileUtil;
import file.MessageLogger;
import java.io.FileOutputStream;

/**
 * Helps to manage a configuration file and its settings. It Works as a wrapper 
 * for the class java.util.Properties
 *
 * @author Daniel Plaza
 */
public class Config extends MessageLogger {

    private File configPath;
    private Properties p;

    /**
     * Creates the Configuration class
     *
     * @param configFilePath
     * @param configLogger if null no log output
     */
    public Config(File configFilePath, File configLogger) {
        super(configLogger, true);
        this.configPath = configFilePath;
        p = new Properties();
    }
    
    /**
     * The same as the other constructor but in this case the log output 
     * messages will go through console
     * @param configFilePath 
     */
    public Config(File configFilePath){
        super();
        this.configPath = configFilePath;
        p = new Properties();
    }    

    /**
     * Changes the value of a property
     *
     * @param setting the setting to be created/modified
     * @param newValue the new value of the setting
     */
    public void setSetting(String setting, String newValue) {
        p.setProperty(setting, newValue);
        writeToLog(setting + "=" + newValue);
    }

    /**
     * Read the properties file. If an error happens it will execute the
     * ReadingError method execute. The param can be null.
     */
    public void readProperties(ReadingError re) {
        InputStream input = null;
        try {
            input = new FileInputStream(configPath);
            p.load(input);
            writeToLog("Configuration file loaded");
        } catch (IOException ex) {
            if(re!=null)
                re.execute();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Read the properties file. If an error happens it will create an empty
     * configuration file
     */
    public void readProperties() {
        readProperties(() -> {
            save(true);
        });
    }

    /**
     * Save the settings to the configuration file
     * @param backup
     */
    public void save(boolean backup) {
        FileOutputStream output;
        try {
            if(configPath.exists() && backup)
                FileUtil.copyFile(configPath, new File(configPath + ".backup")); 
            output = new FileOutputStream(configPath);
            p.store(output, null);
            writeToLog("Configuration file saved");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
    }

    /*
     * Getters and Setters
     */
    public File getConfigPath() {
        return configPath;
    }

    public void setConfigPath(File configPath) {
        this.configPath = configPath;
    }

    public interface ReadingError {
        public void execute();
    }
}
