package com.jrd.itmas_server.api.rest;

import com.jrd.itmas_server.api.rest.dto.UserDTO;
import com.jrd.itmas_server.domain.user.User;
import com.jrd.itmas_server.repository.UserRepository;
import com.jrd.itmas_server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Kuba on 2016-07-13.
 */
@Component
@Transactional
public class AccountApi {
    private final Logger log = LoggerFactory.getLogger(AccountApi.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    public UserDTO getUserWithAuthorities() {
        return new UserDTO(userService.getUserWithAuthorities());
    }

    public UserDTO getUserWithAuthorities(String login) {
        return new UserDTO(userService.getUserWithAuthorities(login));
    }

    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(u -> new UserDTO(u))
                .collect(Collectors.toList());
    }

    public void deactivateUser(String login) {
        userService.deactivateUser(login);
    }

    public Optional<UserDTO> createUserInformation(UserDTO userDTO) {
        return userRepository.findOneByLogin(userDTO.getLogin())
                .map(user -> Optional.of(new UserDTO(user)))
                .orElseGet(() -> {
                    UserDTO us = produceUser(userDTO);
                    log.info("User created = " + us.toString());
                    return Optional.empty();
                });
    }

    private UserDTO produceUser(UserDTO userDTO) {
        User user = userService.createUserInformation(userDTO.getLogin(), userDTO.getPassword(),
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail().toLowerCase());
        return new UserDTO(user);
    }
}
