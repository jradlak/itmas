package com.jrd.itmas_client.servercom;

import com.jrd.itmas_client.serververcom.ServerCommunicator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Kuba on 2016-05-29.
 */
public class ServerCommunicatorTest {

    private ServerCommunicator serverCommunicator;

    @Before
    public void setup() {
        serverCommunicator = new ServerCommunicator();
    }

    @Test
    public void connectToServerTest() throws IOException {
        serverCommunicator.connectToServer();
    }
}
