package com.jrd.itmas_server.api.rest.dto;

import com.jrd.itmas_server.domain.task.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by jakub on 30.07.16.
 */
@Getter
@Setter
public class TaskDTO {
    private Long id;

    private String name;

    private String description;

    private String status;

    private LocalDateTime creationTime;

    private LocalDateTime modificationTime;

    private LocalDateTime deadLineTime;

    private Long categoryId;

    private String creatorLogin;

    private String executorLogin;

    private String controllerLogin;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.status = task.getStatus().toString();
        this.creationTime = task.getCreationTime();
        this.modificationTime = task.getModificationTime();
        this.categoryId = task.getCategory().getId();
        this.creatorLogin = task.getCreator().getLogin();
        this.executorLogin = task.getExecutor().getLogin();
        this.controllerLogin = task.getController().getLogin();
    }
}
