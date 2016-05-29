package com.jrd.itmas_server.security;

import com.jrd.itmas_server.domain.User;
import com.jrd.itmas_server.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jakub on 07.05.16.
 */
public class UserDetailServiceTest {

    private UserDetailsService userDetailsService;

    private UserRepository userRepository;

    private final static String userLogin = "userlogin";

    @Before
    public void setup() {
        userRepository = mock(UserRepository.class);
        when(userRepository.findOneByLogin(userLogin)).thenReturn(Optional.of(new User(userLogin, "pass")));

        userDetailsService = new UserDetailsServiceImpl();
        ((UserDetailsServiceImpl)userDetailsService).setUserRepository(userRepository);
    }

    @Test
    public void loadUserByUserNameTest() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userLogin);
        Assert.assertTrue(userLogin.equals(userDetails.getUsername()));
    }
}
