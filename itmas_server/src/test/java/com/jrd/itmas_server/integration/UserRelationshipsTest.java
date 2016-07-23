package com.jrd.itmas_server.integration;

import com.jrd.itmas_server.ItmasServerApplication;
import com.jrd.itmas_server.domain.task.Task;
import com.jrd.itmas_server.domain.user.User;
import com.jrd.itmas_server.repository.TaskRepository;
import com.jrd.itmas_server.repository.UserRepository;
import com.jrd.itmas_server.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Kuba on 2016-07-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ItmasServerApplication.class)
@WebAppConfiguration
@IntegrationTest
public class UserRelationshipsTest {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private TaskRepository taskRepository;

    @Test
    @Transactional
    public void userTasksTest() {
        List<User> users = userService.getAllUsers(); // userRepository.findAll();

        Assert.assertTrue(users.size() > 0);
        Assert.assertTrue(users.get(0).getControlledTasks().size() > 0);
        Assert.assertTrue(users.get(0).getCreatedTasks().size() > 0);
        Assert.assertTrue(users.get(0).getProcessingTasks().size() > 0);
    }

    @Test
    @Transactional
    public void tasksTest() {
        Task task = taskRepository.findOne(1L);

        Assert.assertTrue(task != null);

        Assert.assertTrue("BUG".equals(task.getCategory().getName()));
        Assert.assertTrue("user".equals(task.getCreator().getLogin()));
        Assert.assertTrue("user".equals(task.getExecutor().getLogin()));
        Assert.assertTrue("user".equals(task.getController().getLogin()));
    }
}
