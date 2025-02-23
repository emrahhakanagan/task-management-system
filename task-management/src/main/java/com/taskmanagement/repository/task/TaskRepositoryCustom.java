package com.taskmanagement.repository.task;

import com.taskmanagement.dto.task.TaskFilterDTO;
import com.taskmanagement.model.Task;
import java.util.List;

public interface TaskRepositoryCustom {
    List<Task> findTasksByFilters(TaskFilterDTO filter);
}