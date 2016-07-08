package com.jrd.itmas_client.infrastructure.validation;

import com.jrd.itmas_client.infrastructure.utils.Literals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by jakub on 23.06.16.
 */
public class UserDataValidator {

    private final static String wPattern = "\\w+";

    private final static String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final static String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    private final static String rolePattern = "ROLE USER|ROLE ADMIN";

    public static final String login = "Login";

    public static final String firstName = "First Name";

    public static final String lastName = "Last Name";

    public static final String email = "E-mail";

    public static final String role = "Role";

    public static final String password = "Password";

    public static final String roleAdmin = "ROLE ADMIN";

    public static final String roleUser = "ROLE USER";

    private String[][] validationPattern = {
            {login, wPattern},
            {firstName, wPattern},
            {lastName, wPattern},
            {password, passwordPattern},
            {role, rolePattern},
            {email, emailPattern}
    };

    private Map<String, String> patterns = new HashMap<>();

    public UserDataValidator() {
        Arrays.asList(validationPattern).stream().forEach(v -> patterns.put(v[0], v[1]));
    }

    public ValidationResult validateUserDataLine(String[] line) {
        if (patterns.containsKey(line[0])) {
            Pattern pattern = Pattern.compile(patterns.get(line[0]));
            if (!pattern.matcher(line[1]).matches()) {
                return new ValidationResult(false,
                        Literals.get().getValidation().getUserDataValidation()
                                .getWordDoesNotMatch()
                                .replace("@w1", line[1]).replace("@w2", line[0]));
            }

            return new ValidationResult(true,
                    Literals.get().getValidation().getUserDataValidation()
                            .getLineCorrect()
                            .replace("@w1", line[0]). replace("@w2", line[1]));
        } else {
            return new ValidationResult(false,
                    Literals.get().getValidation().getUserDataValidation()
                            .getCannotRecognize()
                            .replace("@w1", line[0]));
        }
    }
}