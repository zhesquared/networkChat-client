package client;

import logger.LogType;
import logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageListener implements Runnable {

    private BufferedReader in;
    private Logger logger = Logger.getInstance();

    public MessageListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (readMessage()) ;
        try {
            in.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean readMessage() {
        try {
            String message = in.readLine();
            if (message != null) {
                System.out.println(message);
                logger.log(message, LogType.MESSAGE, true);
            }
        } catch (IOException exception) {
            String troubles = "connection lost";
            System.out.println(troubles);
            logger.log(troubles, LogType.ERROR, false);
            return false;
        }
        return true;
    }
}
