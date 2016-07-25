package com.jrd.itmas_server.service;

import com.jrd.itmas_server.domain.task.Task;
import com.jrd.itmas_server.domain.user.User;
import com.jrd.itmas_server.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Kuba on 2016-07-25.
 */
public class TaskServiceTest {

    private static final String TEST_CREATOR = "testCreator";

    private static final String TEST_CONTROLER = "testControler";

    private static final String TEST_EXECUTOR = "testExecutor";

    private static final String CONTROLLED_TASK_1 = "controlledTask1";

    private static final String CREATED_TASK_1 = "createdTask1";

    private static final String PROCESSING_TASK_1 = "processingTask1";

    private UserRepository userRepository;

    private UserService userService;

    private TaskService taskService;

    private User controler;

    private User creator;

    private User executor;

    private List<Task> createdTasks;

    private List<Task> processingTasks;

    private List<Task> controlledTasks;

    @Before
    public void setup() {
        this.controler = new User(1, TEST_CONTROLER);
        this.creator = new User(2, TEST_CREATOR);
        this.executor = new User(3, TEST_EXECUTOR);

        controlledTasks = Arrays.asList(
                new Task(1L, CONTROLLED_TASK_1, creator, controler, executor)
            );

        createdTasks = Arrays.asList(
                new Task(2L, CREATED_TASK_1, creator, controler, executor)
            );

        processingTasks = Arrays.asList(
                new Task(3L, PROCESSING_TASK_1, creator, controler, executor)
            );

        this.creator.setCreatedTasks(new HashSet<>(createdTasks));
        this.controler.setControlledTasks(new HashSet<>(controlledTasks));
        this.executor.setProcessingTasks(new HashSet<>(processingTasks));

        userRepository = mock(UserRepository.class);

        when(userRepository.findOneByLogin(TEST_CREATOR)).thenReturn(Optional.of(this.creator));
        when(userRepository.findOneByLogin(TEST_CONTROLER)).thenReturn(Optional.of(this.controler));
        when(userRepository.findOneByLogin(TEST_EXECUTOR)).thenReturn(Optional.of(this.executor));

        userService = new UserService();
        userService.setUserRepository(userRepository);

        taskService = new TaskService();
        taskService.setUserService(userService);
    }

    @Test
    public void testGetCreatedTasks() {
        List<Task> createdTasks = taskService.getCreatedTasks(TEST_CREATOR);

        Assert.assertTrue(createdTasks.size() > 0);
        Assert.assertTrue(CREATED_TASK_1.equals(createdTasks.get(0).getName()));
        Assert.assertTrue(creator.getLogin().equals(createdTasks.get(0).getCreator().getLogin()));
    }
}
