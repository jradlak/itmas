package com.jrd.itmas_server.api;

import com.jrd.itmas_server.ItmasServerApplication;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Kuba on 2016-08-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ItmasServerApplication.class)
@WebAppConfiguration
@IntegrationTest
@Ignore
public class ResourceTest {
}
