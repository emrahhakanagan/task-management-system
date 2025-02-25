package com.taskmanagement.service.task;

import com.taskmanagement.dto.task.TaskDTO;
import com.taskmanagement.dto.task.TaskFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(UUID id);
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO updateTask(UUID id, TaskDTO taskDTO, Long userId);
    void deleteTask(UUID id);
    Page<TaskDTO> getTasksByFilters(TaskFilterDTO filter, Pageable pageable);
}