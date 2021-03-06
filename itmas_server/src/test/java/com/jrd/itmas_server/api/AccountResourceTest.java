package com.jrd.itmas_server.api;

import com.jrd.itmas_server.api.rest.AccountApi;
import com.jrd.itmas_server.api.rest.AccountResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.MediaType;
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
public class AccountResourceTest extends ResourceTest {

    @Inject
    private AccountApi accountApi;

    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        AccountResource accountResource = new AccountResource();
        ReflectionTestUtils.setField(accountResource, "accountApi", accountApi);

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
