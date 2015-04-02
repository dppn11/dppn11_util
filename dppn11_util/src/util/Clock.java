
package util;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Daniel Plaza
 */
public class Clock {
    
    public static int ONE_SEC=1000;
    private long period=1000;
    
    private Timer timer;
    private TimerTask task;
    private final ClockAction action;
    private boolean isRunning=false;
    
    /**
     * Creates a new clock
     * @param action to be performed every period
     * @param period period in milliseconds
     */
    public Clock(ClockAction action,int period){
        this.action=action;
        this.period=(long)period;
    }
    
    /**
     * The same as the other constructor but with a default period value of 1 sec.
     * @param action to be performed every period
     */
    public Clock(ClockAction action){
        this(action,ONE_SEC);
    }
    
    /**
     * Starts the clock
     */
    public void start(){
        if(isRunning==false){
            timer=new Timer();
            task=new TimerTask() {
                @Override
                public void run() {
                    action.execute();
                }
            };
            timer.scheduleAtFixedRate(task, 0, period);
            isRunning=true;
        }
    }
    
    /**
     * Pauses the clock
     */
    public void stop(){
        timer.cancel();
        isRunning=false;
    }
    
    
    
    public interface ClockAction{
        /**
         * Action to be performed in each period of the clock
         */
        public void execute();
    }
}
