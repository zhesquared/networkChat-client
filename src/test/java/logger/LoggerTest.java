package logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTest {

    private Logger logger;

    @Test
    public void messageInLoggerTest() {
        String message = "Hello all";

        logger = Logger.getInstance();

        boolean actual = logger.log(message, LogType.MESSAGE, false);

        assertTrue(actual);
    }
}
