package com.jrd.itmas_client.infrastructure.utils;

import com.esotericsoftware.yamlbeans.YamlException;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by Kuba on 2016-07-05.
 */
public class LiteralsTest {

    @Test
    public void getLiteralTest() throws FileNotFoundException, YamlException {
        Literals.prepareYaml("PL");

        String authenticationEx = Literals.get().getExceptions().getServerCommunicationExceptions().getAuthentication();
        String userGet = Literals.get().getExceptions().getServerCommunicationExceptions().getUserDataGet();
        String userIterpret = Literals.get().getExceptions().getServerCommunicationExceptions().getGetUserDataInterpret();

        Assert.assertTrue("There is problem with authentication.".equals(authenticationEx));
        Assert.assertTrue("There is problem with get user data from server.".equals(userGet));
        Assert.assertTrue("There is problem with interpretation of user data from server.".equals(userIterpret));
    }
}
