package com.jrd.itmas_client.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jakub on 05.06.16.
 */
public class Command {

    private final static String wPattern = "('\\w*' )?";

    public enum CommandType {
        USER_ADD("user " + wPattern + "add"),
        USER_REMOVE("user " + wPattern + "remove"),
        USER_SHOW("user " + wPattern + "show"),
        USER_EDIT("user " + wPattern + "edit");

        private String pattern;

        CommandType(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

    private static final CommandType[] commandTypes = {
            CommandType.USER_ADD,
            CommandType.USER_REMOVE,
            CommandType.USER_SHOW,
            CommandType.USER_EDIT
    };

    private CommandType commandType;

    private String[] parameters;

    public Command(String rawCommand) {
        extract(rawCommand);
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getParameters() {
        return parameters;
    }

    public String getParameter(int i) {
        if (parameters.length > i) {
            return parameters[i];
        } else {
            return "";
        }
    }

    private void extract(final String rawCommand) {
        Arrays.asList(commandTypes).stream().forEach(c -> {
            Pattern pattern = Pattern.compile(c.getPattern());
            Matcher m = pattern.matcher(rawCommand);
            if (m.matches()) {
                commandType = c;
                parameters = extractParameters(m.group(0));
                return;
            }
        });
    }

    private String[] extractParameters(String command) {
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher m = pattern.matcher(command);
        if (m.find()) {
            List<String> params = new ArrayList<>();
            for (int i = 0; i < m.groupCount(); i++) {
                params.add(m.group(i).replace("'", ""));
            }

            return params.toArray(new String[] {});
        }

        return new String[] {};
    }
}