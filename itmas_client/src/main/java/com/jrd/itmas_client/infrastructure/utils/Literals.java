package com.jrd.itmas_client.infrastructure.utils;



import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Kuba on 2016-07-05.
 */
public class Literals {

    private static Literals literals;

    public static Literals get() {
        if (literals == null) {
            try {
                prepareYaml("PL");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (YamlException e) {
                e.printStackTrace();
            }
        }

        return literals;
    }

    public static void prepareYaml(String lang) throws FileNotFoundException, YamlException {
        YamlReader reader = new YamlReader(new FileReader("src/main/resources/literals.yml"));
        literals = reader.read(Literals.class);
    }


    private Exceptions exceptions;

    private Validation validation;

    public Exceptions getExceptions() {
        return exceptions;
    }

    public void setExceptions(Exceptions exceptions) {
        this.exceptions = exceptions;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public static Literals getLiterals() {
        return literals;
    }

    public static void setLiterals(Literals literals) {
        Literals.literals = literals;
    }

    public static class Exceptions {

        private ServerCommunicationExceptions serverCommunicationExceptions;

        private CommandExecutionExceptions commandExecutionExceptions;

        public ServerCommunicationExceptions getServerCommunicationExceptions() {
            return serverCommunicationExceptions;
        }

        public void setServerCommunicationExceptions(ServerCommunicationExceptions serverCommunicationExceptions) {
            this.serverCommunicationExceptions = serverCommunicationExceptions;
        }

        public CommandExecutionExceptions getCommandExecutionExceptions() {
            return commandExecutionExceptions;
        }

        public void setCommandExecutionExceptions(CommandExecutionExceptions commandExecutionExceptions) {
            this.commandExecutionExceptions = commandExecutionExceptions;
        }

        public static class ServerCommunicationExceptions {
            private String authentication;

            private String userDataGet;

            private String userDataInterpretation;

            private String userDataConversion;

            public String getAuthentication() {
                return authentication;
            }

            public void setAuthentication(String authentication) {
                this.authentication = authentication;
            }

            public String getUserDataGet() {
                return userDataGet;
            }

            public void setUserDataGet(String userDataGet) {
                this.userDataGet = userDataGet;
            }

            public String getUserDataInterpretation() {
                return userDataInterpretation;
            }

            public void setUserDataInterpretation(String userDataInterpretation) {
                this.userDataInterpretation = userDataInterpretation;
            }

            public String getUserDataConversion() {
                return userDataConversion;
            }

            public void setUserDataConversion(String userDataConversion) {
                this.userDataConversion = userDataConversion;
            }
        }

        public static class CommandExecutionExceptions {
            private String errorReadingFile;

            public String getErrorReadingFile() {
                return errorReadingFile;
            }

            public void setErrorReadingFile(String errorReadingFile) {
                this.errorReadingFile = errorReadingFile;
            }
        }
    }

    public static class Validation {

        private UserDataValidation userDataValidation;

        public UserDataValidation getUserDataValidation() {
            return userDataValidation;
        }

        public void setUserDataValidation(UserDataValidation userDataValidation) {
            this.userDataValidation = userDataValidation;
        }

        public static class UserDataValidation {

            private String wordDoesNotMatch;

            private String lineCorrect;

            private String cannotRecognize;

            public String getWordDoesNotMatch() {
                return wordDoesNotMatch;
            }

            public void setWordDoesNotMatch(String wordDoesNotMatch) {
                this.wordDoesNotMatch = wordDoesNotMatch;
            }

            public String getLineCorrect() {
                return lineCorrect;
            }

            public void setLineCorrect(String lineCorrect) {
                this.lineCorrect = lineCorrect;
            }

            public String getCannotRecognize() {
                return cannotRecognize;
            }

            public void setCannotRecognize(String cannotRecognize) {
                this.cannotRecognize = cannotRecognize;
            }
        }
    }
}
