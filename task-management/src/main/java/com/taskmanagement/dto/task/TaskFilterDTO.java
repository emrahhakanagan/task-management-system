package com.taskmanagement.dto.task;

import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;
import com.taskmanagement.model.Task;

import java.time.LocalDateTime;

public class TaskFilterDTO {
    private String title;
    private Status status;
    private Priority priority;
    private LocalDateTime createdFrom;
    private LocalDateTime createdTo;
    private LocalDateTime updatedFrom;
    private LocalDateTime updatedTo;

    public TaskFilterDTO() {
    }

    public TaskFilterDTO(Task task) {
        this.title = task.getTitle();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.createdFrom = task.getCreatedAt();
        this.createdTo = task.getCreatedAt();
        this.updatedFrom = task.getUpdatedAt();
        this.updatedTo = task.getUpdatedAt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(LocalDateTime createdFrom) {
        this.createdFrom = createdFrom;
    }

    public LocalDateTime getCreatedTo() {
        return createdTo;
    }

    public void setCreatedTo(LocalDateTime createdTo) {
        this.createdTo = createdTo;
    }

    public LocalDateTime getUpdatedFrom() {
        return updatedFrom;
    }

    public void setUpdatedFrom(LocalDateTime updatedFrom) {
        this.updatedFrom = updatedFrom;
    }

    public LocalDateTime getUpdatedTo() {
        return updatedTo;
    }

    public void setUpdatedTo(LocalDateTime updatedTo) {
        this.updatedTo = updatedTo;
    }
}
