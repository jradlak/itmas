package com.jrd.itmas_server.api.rest;

import com.jrd.itmas_server.api.rest.dto.UserDTO;
import com.jrd.itmas_server.domain.User;
import com.jrd.itmas_server.repository.UserRepository;
import com.jrd.itmas_server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

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
