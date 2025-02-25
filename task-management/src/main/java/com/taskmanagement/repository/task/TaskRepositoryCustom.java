package com.taskmanagement.repository.task;

import com.taskmanagement.dto.task.TaskFilterDTO;
import com.taskmanagement.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskRepositoryCustom {
    Page<Task> findTasksByFilters(TaskFilterDTO filter, Pageable pageable);
}