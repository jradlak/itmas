package com.jrd.itmas_server.api;

import com.jrd.itmas_server.ItmasServerApplication;
import com.jrd.itmas_server.api.rest.AccountResource;
import com.jrd.itmas_server.repository.UserRepository;
import com.jrd.itmas_server.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jakub on 13.06.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ItmasServerApplication.class)
@WebAppConfiguration
@IntegrationTest
public class AccountResourceTest  {
    private RestTemplate template = new TestRestTemplate();

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        AccountResource accountResource = new AccountResource();
        ReflectionTestUtils.setField(accountResource, "userRepository", userRepository);
        ReflectionTestUtils.setField(accountResource, "userService", userService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(accountResource).build();
    }

    @Test
    public void testGetAccountByLogin() throws Exception {
        mockMvc.perform(get("/api/accountByLogin/admin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
