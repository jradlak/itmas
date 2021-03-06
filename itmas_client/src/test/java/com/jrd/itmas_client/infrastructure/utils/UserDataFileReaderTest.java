package com.jrd.itmas_client.infrastructure.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kuba on 2016-07-05.
 */
public class UserDataFileReaderTest {

    private final static String fileName = "userDataTest.txt";

    @Test
    public void readUserDataTest() throws IOException {
        List<String[]> result = UserDataFileReader.readUserData(fileName);

        Assert.assertTrue(result.size() > 0);
        Assert.assertTrue(result.get(0)[0].equals("First Name"));
        Assert.assertTrue(result.get(0)[1].equals("Jakub"));
    }
}

