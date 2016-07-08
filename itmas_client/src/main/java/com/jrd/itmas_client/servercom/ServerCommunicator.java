package com.jrd.itmas_client.servercom;

import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.servercom.dto.UserDTO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by jakub on 12.06.16.
 */
public interface ServerCommunicator {
    UserDTO getUserDataFromServer(String userLogin) throws ServerCommunicationException;
    UserDTO userAdd(UserDTO userDTO) throws ServerCommunicationException;
}
