package com.taskmanagement.service.task;

import com.taskmanagement.dto.task.TaskDTO;
import com.taskmanagement.model.Task;
import com.taskmanagement.repository.task.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return new TaskDTO(task);
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        return new TaskDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO updateTask(UUID id, TaskDTO taskDTO, Long userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Проверяем, является ли пользователь исполнителем
        if (!task.getAssignedUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this task");
        }

        task.setStatus(taskDTO.getStatus());
        return new TaskDTO(taskRepository.save(task));
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}