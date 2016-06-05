package com.jrd.itmas_client.interpreter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jakub on 05.06.16.
 */
public class Command {
    public enum CommandType {
        USER_ADD("user \\w* ?add"),
        USER_REMOVE("user \\w* ?remove"),
        USER_SHOW("user \\w* ?show"),
        USER_EDIT("user \\w* ?edit");

        private String pattern;

        CommandType(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

    private CommandType[] commandValues = {
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

    private void extract(final String rawCommand) {
        Arrays.asList(commandValues).stream().forEach(c -> {
            Pattern pattern = Pattern.compile(c.getPattern());
            Matcher m = pattern.matcher(rawCommand);

            if (m.matches()) {
                commandType = c;
                //parameters =
            };
        });
    }
}