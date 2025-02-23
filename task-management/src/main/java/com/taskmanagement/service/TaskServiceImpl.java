package com.taskmanagement.service;

import com.taskmanagement.dto.task.TaskDTO;
import com.taskmanagement.model.Task;
import com.taskmanagement.repository.TaskRepository;
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
    public TaskDTO updateTask(UUID id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());

        return new TaskDTO(taskRepository.save(task));
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}