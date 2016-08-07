package com.jrd.itmas_client.ui;

import com.jrd.itmas_client.infrastructure.exceptions.CommandSyntaxException;
import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.infrastructure.validation.ValidationResult;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_server.api.rest.dto.TaskDTO;

import java.io.PrintStream;
import java.util.Arrays;
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
        } else if (ex instanceof ServerCommunicationException) {
            ou.println("Server communication error. " + ex.getMessage());
        } else if (ex instanceof CommandSyntaxException) {
            ou.println("Command execution error." + ex.getMessage());
        }else {
            ou.println(ex.getMessage());
            ex.printStackTrace();
            // TODO: extend this
        }
    }

    public void printUserData(UserDTO userDTO) {
        printMessage("User INFO:");
        printMessage("-------------------------");
        printMessage("Login:        " + userDTO.getLogin());
        printMessage("First Name:   " + userDTO.getFirstName());
        printMessage("Last Name:    " + userDTO.getLastName());
        printMessage("E-mail:       " + userDTO.getEmail());
        printMessage("Authorities:  " + userDTO.getAuthorities());
    }

    public void printTasksData(String userLogin, List<TaskDTO> taskDTOs) {
        printMessage("User " + userLogin + " tasks: ");
        printMessage("-------------------------");
        for (TaskDTO t : taskDTOs) {
            printMessage("Name:          " + t.getName());
            printMessage("Description:   " + t.getDescription());
            printMessage("Category:      " + t.getCategoryName());
            printMessage("Creation time: " + t.getCreationTime());
            printMessage("Deadline time: " + t.getDeadLineTime());
            printMessage("Creator login: " + t.getCreatorLogin());
            printMessage("Status:        " + t.getStatus());
        }
     }

    public void showValidationErrors(List<ValidationResult> results) {
        results.stream().forEach(r -> printMessage(r.getErrorDescription()));
    }
}
