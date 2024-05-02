

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class used to record message show in client*
 */
public class LogOutput extends UnicastRemoteObject implements IActivity {

    private Logger logger;

    /**
     * logOutput component subscribes on event bus to show events*
     */
    public LogOutput() throws RemoteException {
        super();

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


    @Override
    public String execute(String param) throws RemoteException {
        logger.info(param);
        return null;
    }
}
