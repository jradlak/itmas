package com.jrd.itmas_server.service;

import com.jrd.itmas_server.domain.task.Task;
import com.jrd.itmas_server.domain.user.User;
import com.jrd.itmas_server.repository.TaskRepository;
import com.jrd.itmas_server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Kuba on 2016-07-18.
 */
@Service
@Transactional
public class TaskService {
    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    private TaskRepository taskRepository;

    private UserService userService;

    @Inject
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //TODO: make better exception system
    public List<Task> getAllUserTasks(String userLogin) throws Exception {
        Optional<User> user = userService.getUserWithAllTasks(userLogin);
        if (!user.isPresent()) {
            throw new Exception("User not found.");
        }

        List<Task> result = new ArrayList<>();
        result.addAll(user.get().getControlledTasks());
        result.addAll(user.get().getCreatedTasks());
        result.addAll(user.get().getProcessingTasks());
        return result;
    }

    public List<Task> getCreatedTasks(String userLogin) {
        User user = userService.getUserWithCreatedTasks(userLogin);
        List<Task> result = new ArrayList<>();
        result.addAll(user.getCreatedTasks());
        return result;
    }
}
