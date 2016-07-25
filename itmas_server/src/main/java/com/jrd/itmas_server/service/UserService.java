package com.jrd.itmas_server.service;

import com.jrd.itmas_server.api.rest.dto.UserDTO;
import com.jrd.itmas_server.domain.user.Authority;
import com.jrd.itmas_server.domain.user.User;
import com.jrd.itmas_server.repository.AuthorityRepository;
import com.jrd.itmas_server.repository.UserRepository;
import com.jrd.itmas_server.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jakub on 24.04.16.
 */
@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    @Inject
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Inject
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername()).get();
        user.getAuthorities().size();
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(String userLogin) {
        User user = userRepository.findOneByLogin(userLogin).get();
        user.getAuthorities().size();
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserWithControlledTasks(String userLogin) {
        User user = userRepository.findOneByLogin(userLogin).get();
        user.getControlledTasks().size();
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserWithCreatedTasks(String userLogin) {
        User user = userRepository.findOneByLogin(userLogin).get();
        user.getCreatedTasks().size();
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserWithProcessingTasks(String userLogin) {
        User user = userRepository.findOneByLogin(userLogin).get();
        user.getProcessingTasks().size();
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserWithAllTasks(String userLogin) {
        User user = userRepository.findOneByLogin(userLogin).get();
        user.getProcessingTasks().size();
        user.getCreatedTasks().size();
        user.getControlledTasks().size();
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
    	return userRepository.findAll();
    }

    public User createUserInformation(String login, String password, String firstName, String lastName, String email) {
        User newUser = new User();
        Authority authority = authorityRepository.findOne("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        newUser.setActive(true);
        userRepository.saveAndFlush(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public UserDTO deactivateUser(String login) {
        User user = userRepository.findOneByLogin(login).get();
        user.setActive(false);
        userRepository.saveAndFlush(user);
        return new UserDTO(user);
    }

    public UserDTO disableUser(String login) {
        User user = userRepository.findOneByLogin(login).get();
        user.setEnabled(false);
        userRepository.saveAndFlush(user);
        return new UserDTO(user);
    }
}