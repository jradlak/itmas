package com.jrd.itmas_server.api.rest;

import com.jrd.itmas_server.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Kuba on 2016-07-18.
 */
@Component
@Transactional
public class TaskApi {
    private final Logger log = LoggerFactory.getLogger(TaskApi.class);

    private TaskService taskService;

    @Inject
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
