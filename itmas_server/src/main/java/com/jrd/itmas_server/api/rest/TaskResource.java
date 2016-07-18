package com.jrd.itmas_server.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

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
}
