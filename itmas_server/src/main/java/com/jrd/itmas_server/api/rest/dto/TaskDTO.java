package com.jrd.itmas_server.api.rest.dto;

import com.jrd.itmas_server.domain.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by jakub on 30.07.16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;

    private String name;

    private String description;

    private String status;

    private String creationTime;

    private String modificationTime;

    private String deadLineTime;

    private Long categoryId;

    private String categoryName;

    private String creatorLogin;

    private String executorLogin;

    private String controllerLogin;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.status = task.getStatus().toString();
        this.creationTime = task.getCreationTime().toString();
        this.modificationTime = task.getModificationTime().toString();
        this.deadLineTime = task.getDeadLineTime().toString();
        this.categoryId = task.getCategory().getId();
        this.categoryName = task.getCategory().getName();
        this.creatorLogin = task.getCreator().getLogin();
        this.executorLogin = task.getExecutor().getLogin();
        this.controllerLogin = task.getController().getLogin();
    }
}
