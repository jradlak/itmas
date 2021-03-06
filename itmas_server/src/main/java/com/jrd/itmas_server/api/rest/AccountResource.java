package com.jrd.itmas_server.api.rest;

import com.jrd.itmas_server.api.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

/**
 * Created by jakub on 17.04.16.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private AccountApi accountApi;

    @Inject
    public void setAccountApi(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

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
        return prepareUserDTOResponseEntity(accountApi.getUserWithAuthorities());
    }

    @RequestMapping(value = "/accountByLogin/{login:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getAccountByLogin(@PathVariable String login) {
        log.info("getAccountByLogin, login = " + login);
        return prepareUserDTOResponseEntity(accountApi.getUserWithAuthorities(login));
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerAccount(@RequestBody UserDTO userDTO) {
        return accountApi.createUserInformation(userDTO)
                .map(user -> new ResponseEntity<>("login already in use", HttpStatus.BAD_REQUEST))
                .orElseGet(() -> {
                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }

    @RequestMapping(value = "/deleteAccount/{login:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAccount(@PathVariable String login) {
       accountApi.disableUser(login);
    }

    private ResponseEntity<UserDTO> prepareUserDTOResponseEntity(UserDTO userFromDB) {
        return Optional.ofNullable(userFromDB)
                .map(user -> new ResponseEntity<>(userFromDB, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
