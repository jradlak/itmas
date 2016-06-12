package com.jrd.itmas_server.api.rest;

import com.jrd.itmas_server.api.rest.dto.UserDTO;
import com.jrd.itmas_server.domain.User;
import com.jrd.itmas_server.repository.UserRepository;
import com.jrd.itmas_server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by jakub on 17.04.16.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    /**
     * GET  /authenticate -> check if the user is authenticated, and return its login.
     */
    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getAccount() {
        log.info("getAccount");
        return prepareUserDTOResponseEntity(userService.getUserWithAuthorities());
    }

    @RequestMapping(value = "/accountByLogin/{login:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getAccountByLogin(@PathVariable String login) {
        log.info("getAccountByLogin, login = " + login);
        return prepareUserDTOResponseEntity(userService.getUserWithAuthorities(login));
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> registerAccount(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {
        return userRepository.findOneByLogin(userDTO.getLogin())
                .map(user -> new ResponseEntity<>("login already in use", HttpStatus.BAD_REQUEST))
                        .orElseGet(() -> {
                            User user = userService.createUserInformation(userDTO.getLogin(), userDTO.getPassword(),
                                    userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail().toLowerCase());
                            log.info("User created = " + user.toString());
                            return new ResponseEntity<>(HttpStatus.CREATED);
                        });
    }

    private ResponseEntity<UserDTO> prepareUserDTOResponseEntity(User userFromDB) {
        return Optional.ofNullable(userFromDB)
                .map(user -> new ResponseEntity<>(new UserDTO(user), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
