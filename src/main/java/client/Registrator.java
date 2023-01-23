package client;

import logger.LogType;
import logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Registrator {

    private BufferedReader in;
    private PrintWriter out;
    private Logger logger = Logger.getInstance();

    public Registrator(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public boolean register() {
        try {
            String serverResponse = in.readLine();
            System.out.println(serverResponse);
            Scanner scanner = new Scanner(System.in);
            String username;
            do {
                username = scanner.nextLine();
                out.println(username);
                serverResponse = in.readLine();
                System.out.println(serverResponse);
            } while (serverResponse.contains("please use another one"));
            String join = "you joined to chat";
            logger.log(join, LogType.INFO, false);
            return true;
        } catch (IOException exception) {
            String trouble = "connection lost";
            System.out.println(trouble);
            logger.log(trouble, LogType.ERROR, false);
            return false;
        }
    }
}
