

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class used to record message show in client*
 */
public class LogOutput implements Observer {

    private Logger logger;

    /**
     * logOutput component subscribes on event bus to show events*
     */
    public LogOutput(){
        EventBus.subscribeTo(EventBus.EV_SHOW,this);

        //create a logger,reference: https://www.cnblogs.com/lincj/p/5737064.html
        logger = Logger.getLogger(LogOutput.class.getName());
        logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler=new FileHandler("outputLog.log");

            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * When showing, transfer it to string ,and writer it *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        logger.info(arg.toString());
    }

}
