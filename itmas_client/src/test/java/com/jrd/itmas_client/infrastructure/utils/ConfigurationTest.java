package com.jrd.itmas_client.infrastructure.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jakub on 05.06.16.
 */
public class ConfigurationTest {

    private Configuration configuration;

    @Before
    public void setup() throws IOException {
        configuration = new Configuration(".itmas");
    }

    @Test
    public void testConfiguration() {
        Assert.assertTrue(configuration != null);
    }

    @Test
    public void testGetProperty() {
        String propUser = configuration.getProperty(Configuration.Keys.USER);
        String propPassword = configuration.getProperty(Configuration.Keys.PASSWORD);

        Assert.assertTrue("admin".equals(propUser));
        Assert.assertTrue("user".equals(propPassword));
    }
}
