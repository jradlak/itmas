package com.jrd.itmas_server.api.rest;

import com.jrd.itmas_server.api.rest.dto.UserDTO;
import com.jrd.itmas_server.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Kuba on 2016-07-16.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private AccountApi accountApi;

    @Inject
    public void setAccountApi(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<UserDTO>> getAll() {
        log.info("getAll");
        List<UserDTO> users = accountApi.getAllUsers();
        return prepareListUserDTOResponseEntity(users);
    }

    private ResponseEntity<List<UserDTO>> prepareListUserDTOResponseEntity(List<UserDTO> users) {
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
