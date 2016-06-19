package com.jrd.itmas_client.infrastructure.exceptions;

/**
 * Created by jakub on 19.06.16.
 */
public class ServerCommunicationException extends Exception {
    public ServerCommunicationException(String message) {
        super(message);
    }

    public ServerCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
