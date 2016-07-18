package com.jrd.itmas_server.service;

import com.jrd.itmas_server.domain.Task;
import com.jrd.itmas_server.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-07-18.
 */
@Service
@Transactional
public class TaskService {
    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    private TaskRepository taskRepository;

    @Inject
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllUserTasks(String userLogin) {
        return new ArrayList<>();
    }
}
