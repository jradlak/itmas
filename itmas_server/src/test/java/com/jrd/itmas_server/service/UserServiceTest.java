package com.jrd.itmas_server.service;

import com.jrd.itmas_server.domain.user.Authority;
import com.jrd.itmas_server.domain.user.User;
import com.jrd.itmas_server.repository.AuthorityRepository;
import com.jrd.itmas_server.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jakub on 07.05.16.
 */
public class UserServiceTest {
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    private UserService userService;

    private static final Authority userAuthority = new Authority("ROLE_USER");

    @Before
    public void setup() {
        passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };

        userRepository = mock(UserRepository.class);
        authorityRepository = mock(AuthorityRepository.class);

        when(authorityRepository.findOne("ROLE_USER")).thenReturn(userAuthority);
        when(userRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(new User[] {
            new User("test1", "pass1"), new User("test2", "pass2") })));
        
        userService = new UserService();
        userService.setPasswordEncoder(passwordEncoder);
        userService.setUserRepository(userRepository);
        userService.setAuthorityRepository(authorityRepository);
    }
    
    @Test
    public void getAllUsersTest() {
    	List<User> users = this.userService.getAllUsers();

    	Assert.assertTrue(users.size() > 0);
        Assert.assertTrue("test1".equals(users.get(0).getLogin()) &&
                "pass1".equals(users.get(0).getPassword())
        );
    }

    @Test
    public void createUserInformationTest() {
        String login = "userTest1";
        String password = "userPass";
        String email = "user@email.com";
        String firstName = "userFirstName";
        String lastName = "userLastName";

        User user = userService.createUserInformation(login, password, firstName, lastName, email);

        Assert.assertTrue(login.equals(user.getLogin()));
        Assert.assertTrue(password.equals(user.getPassword()));
        Assert.assertTrue(email.equals(user.getEmail()));
        Assert.assertTrue(user.getAuthorities().size() > 0);
        Assert.assertTrue(user.getAuthorities().contains(userAuthority));
    }
}
