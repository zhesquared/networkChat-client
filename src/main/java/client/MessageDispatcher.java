package client;

import logger.LogType;
import logger.Logger;

import java.io.PrintWriter;
import java.util.Scanner;

public class MessageDispatcher implements Runnable {

    private PrintWriter out;
    private final Scanner scanner = new Scanner(System.in);
    private Logger logger = Logger.getInstance();

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public MessageDispatcher(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        String message;
        while (true) {
            message = scanner.nextLine();
            if (!sendMessage(message))  {
                break;
            }
        }
        System.exit(0);
    }

    public boolean sendMessage(String message) {
        out.println(message);
        if ("/exit".equalsIgnoreCase(message)) {
            String leave = "you have left left chat";
            logger.log(leave, LogType.INFO, false);
            return false;
        } else {
            String userMessage = "you said: " + message;
            logger.log(userMessage, LogType.MESSAGE, false);
        }
        return true;
    }
}
