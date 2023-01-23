package client;

import logger.LogType;
import logger.Logger;
import settings.Settings;

import java.io.*;
import java.net.Socket;

public class Client {

    private MessageListener messageListener;
    private MessageDispatcher messageDispatcher;
    private Registrator registrator;
    private final Logger logger = Logger.getInstance();

    public void setRegistrator(Registrator registrator) {
        this.registrator = registrator;
    }

    public boolean serverConnection() {
        String host = Settings.getProperty("host");
        int port = Integer.parseInt(Settings.getProperty("port"));

        try {
            Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            registrator = new Registrator(in, out);
            registrator.register();

            messageListener = new MessageListener(in);
            Thread listener = new Thread(messageListener);
            listener.setDaemon(true);
            listener.start();

            messageDispatcher = new MessageDispatcher(out);
            Thread sender = new Thread(messageDispatcher);
            sender.start();

        } catch (IOException exception) {
            String troubles = "connection lost";
            System.out.println(troubles);
            logger.log(troubles, LogType.ERROR, false);
            return false;
        }
        return true;
    }
}
