package com.taskmanagement.service;

import com.taskmanagement.dto.task.TaskDTO;
import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(UUID id);
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO updateTask(UUID id, TaskDTO taskDTO);
    void deleteTask(UUID id);
}