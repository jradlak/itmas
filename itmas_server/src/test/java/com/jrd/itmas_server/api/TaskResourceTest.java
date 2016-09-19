package com.jrd.itmas_server.api;

import com.jrd.itmas_server.api.rest.TaskApi;
import com.jrd.itmas_server.api.rest.TaskResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kuba on 2016-08-07.
 */
public class TaskResourceTest extends ResourceTest {

    @Inject
    private TaskApi taskApi;

    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        TaskResource taskResource = new TaskResource();
        ReflectionTestUtils.setField(taskResource, "taskApi", taskApi);

        this.mockMvc = MockMvcBuilders.standaloneSetup(taskResource).build();
    }

    @Test
    public void tesGetAllUsersTasks() throws Exception {
        mockMvc.perform(get("/api/tasks/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("add new table")))
                .andExpect(content().string(containsString("BUG")));
    }

    @Test
    public void testGetTaskById() throws Exception {
        mockMvc.perform(get("/api/tasks/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("add new table")))
                .andExpect(content().string(containsString("BUG")));
    }
}
