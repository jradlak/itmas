package com.jrd.itmas_client.ui;

import com.jrd.itmas_client.infrastructure.exceptions.CommandSyntaxException;
import com.jrd.itmas_client.servercom.dto.UserDTO;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jakub on 06.06.16.
 */
public class UIHandler {
    private static PrintStream ou = System.out;

    Scanner in = new Scanner(System.in);

    public void printMessage(String message) {
        ou.println(message);
    }

    public String askQuestion(String question) {
        ou.print(question);
        String answer = in.nextLine();
        return answer;
    }

    public void printTable(List<String> rows) {
        //TODO
    }

    public void printErrorMessage(Exception ex) {
        if (ex instanceof CommandSyntaxException) {
            ou.println("Syntax Error. " + ex.getMessage());
        } else {
            ou.println(ex.getMessage()); // TODO: extend this
        }
    }

    public void printUserData(UserDTO userDTO) {
        printMessage(userDTO.toString());
    }
}
