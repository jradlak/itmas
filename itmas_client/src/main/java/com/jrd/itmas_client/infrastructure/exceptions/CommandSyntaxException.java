package com.jrd.itmas_client.infrastructure.exceptions;

/**
 * Created by jakub on 18.06.16.
 */
public class CommandSyntaxException extends Exception {
    public CommandSyntaxException(String message) {
        super(message);
    }

    public CommandSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
}
