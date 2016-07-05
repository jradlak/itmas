package com.jrd.itmas_client.infrastructure.utils;

import com.esotericsoftware.yamlbeans.YamlException;
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

        System.out.println()
    }
}
