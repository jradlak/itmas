package com.jrd.itmas_client.infrastructure.validation;

/**
 * Created by jakub on 23.06.16.
 */
public class UserDataValidator {

    private String[][] validationPattern = {
            {"login", "%s"},
            {"name", "%s"},
            {"password", ""}
    };

    public ValidationResult validateUserDataLine(String[] line) {
        return new ValidationResult(true, "");
    }
}
