package client;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientTest {

    private Client client;

    @Test
    public void nonAvailableServer() throws IOException {
        Registrator registrator = mock(Registrator.class);

        client = new Client();
        client.setRegistrator(registrator);

        verify(registrator, never()).register();
        assertFalse(client.serverConnection());
    }
}
