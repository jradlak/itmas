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


    public Literals() {
    }

    public Literals(Exceptions exceptions) {
        this.exceptions = exceptions;
    }

    public Exceptions getExceptions() {
        return exceptions;
    }

    public void setExceptions(Exceptions exceptions) {
        this.exceptions = exceptions;
    }

    public static class Exceptions {
        private ServerCommunicationExceptions serverCommunicationExceptions;

        public ServerCommunicationExceptions getServerCommunicationExceptions() {
            return serverCommunicationExceptions;
        }

        public void setServerCommunicationExceptions(ServerCommunicationExceptions serverCommunicationExceptions) {
            this.serverCommunicationExceptions = serverCommunicationExceptions;
        }

        public static class ServerCommunicationExceptions {
            private String authentication;

            private String userDataGet;

            private String getUserDataInterpret;

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

            public String getGetUserDataInterpret() {
                return getUserDataInterpret;
            }

            public void setGetUserDataInterpret(String getUserDataInterpret) {
                this.getUserDataInterpret = getUserDataInterpret;
            }
        }
    }
}
