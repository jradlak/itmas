package com.jrd.itmas_server.api.rest;

import com.jrd.itmas_server.api.rest.dto.TaskDTO;
import com.jrd.itmas_server.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-07-18.
 */
@RestController
@RequestMapping("/api")
public class TaskResource {

    private final Logger log = LoggerFactory.getLogger(TaskResource.class);

    private TaskApi taskApi;

    @Inject
    public void setTaskApi(TaskApi taskApi) {
        this.taskApi = taskApi;
    }

    @RequestMapping(value = "/tasks/{id:[0-9]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(taskApi.getTaskById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new TaskDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tasks/{login:[a-z]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<List<TaskDTO>> getAllUserTasks(@PathVariable String login) {
        try {
            return new ResponseEntity<>(taskApi.getAllUserTasks(login), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
}
