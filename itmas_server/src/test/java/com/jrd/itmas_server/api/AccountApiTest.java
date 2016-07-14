package com.jrd.itmas_server.api;

import com.jrd.itmas_server.ItmasServerApplication;
import com.jrd.itmas_server.api.rest.AccountApi;
import com.jrd.itmas_server.api.rest.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Kuba on 2016-07-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ItmasServerApplication.class)
@WebAppConfiguration
@IntegrationTest
public class AccountApiTest {

    private final static String admin = "admin";

    private final static String role_admin = "ROLE_ADMIN";

    @Inject
    private AccountApi accountApi;

    @Test
    public void testGetAllUsers() {
        List<UserDTO> allUsers = accountApi.getAllUsers();

        Assert.assertTrue(allUsers.size() > 0);
        Assert.assertTrue(allUsers
                .stream()
                .filter(u -> admin.equals(u.getLogin()))
                .findAny()
                .isPresent());
    }

    @Test
    public void testGetUserWithAuthorites() {
        UserDTO user = accountApi.getUserWithAuthorities(admin);

        Assert.assertTrue(admin.equals(user.getLogin()));
        Assert.assertTrue(user.getAuthorities().size() > 0);
        Assert.assertTrue(user.getAuthorities()
                .stream()
                .filter(a -> role_admin.equals(a))
                .findAny()
                .isPresent());
    }


}
