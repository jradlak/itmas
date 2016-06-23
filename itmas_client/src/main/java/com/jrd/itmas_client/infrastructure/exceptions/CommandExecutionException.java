package com.jrd.itmas_client.infrastructure.exceptions;

/**
 * Created by jakub on 23.06.16.
 */
public class CommandExecutionException extends Exception {
    public CommandExecutionException(String message) {
        super(message);
    }

    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
