package com.jrd.itmas_client.servercom;

import com.jrd.itmas_client.servercom.dto.UserDTO;

import java.io.IOException;

/**
 * Created by jakub on 12.06.16.
 */
public interface ServerCommunicator {
    UserDTO getUserDataFromServer(String userLogin) throws IOException;
}
