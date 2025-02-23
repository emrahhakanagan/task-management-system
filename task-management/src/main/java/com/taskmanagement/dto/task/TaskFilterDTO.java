package com.taskmanagement.dto.task;

import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;

import java.time.LocalDateTime;

public class TaskFilterDTO {
    private String title;
    private Status status;
    private Priority priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public TaskFilterDTO(String title, Status status, Priority priority, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
