package com.jrd.itmas_server.domain.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jrd.itmas_server.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Kuba on 2016-07-18.
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    @JsonIgnore
    @OneToMany
    private TaskCategory category;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "modification_time")
    private LocalDateTime modificationTime;

    @Column(name = "deadline_time")
    private LocalDateTime deadLineTime;

    // relationships -----------------

    @JsonIgnore
    @OneToMany
    private User creator;

    @JsonIgnore
    @OneToMany
    private User executor;


    @JsonIgnore //TODO
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_controlled_tasks",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<User> controllers;

    // ---------------- relationships


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }

    public LocalDateTime getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(LocalDateTime deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getControllers() {
        return controllers;
    }

    public void setControllers(List<User> controllers) {
        this.controllers = controllers;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }
}
