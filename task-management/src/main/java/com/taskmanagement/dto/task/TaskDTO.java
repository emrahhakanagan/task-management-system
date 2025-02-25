package com.taskmanagement.dto.task;

import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;
import com.taskmanagement.model.Task;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskDTO {
    private UUID id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Long assignedUserId;
    private List<CommentDTO> comments;

    public TaskDTO() {}

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.assignedUserId = task.getAssignedUser() != null ? task.getAssignedUser().getId() : null;
        this.comments = task.getComments().stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());

    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}