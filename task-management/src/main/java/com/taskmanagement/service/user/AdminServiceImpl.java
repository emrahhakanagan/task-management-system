package com.taskmanagement.service.user;

import com.taskmanagement.dto.task.CommentDTO;
import com.taskmanagement.dto.task.TaskDTO;
import com.taskmanagement.dto.user.UserFilterDTO;
import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.RoleType;
import com.taskmanagement.enums.Status;
import com.taskmanagement.model.Comment;
import com.taskmanagement.model.Task;
import com.taskmanagement.model.User;
import com.taskmanagement.repository.task.TaskRepository;
import com.taskmanagement.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        task = taskRepository.save(task);
        return new TaskDTO(task);
    }

    @Override
    public TaskDTO updateTask(UUID taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());

        if (taskDTO.getComments() != null) {
            for (CommentDTO commentDTO : taskDTO.getComments()) {
                task.getComments().stream()
                        .filter(comment -> comment.getId().equals(commentDTO.getId())) // Находим нужный комментарий
                        .findFirst()
                        .ifPresent(comment -> comment.setText(commentDTO.getText())); // Обновляем текст
            }
        }

        task = taskRepository.save(task);
        return new TaskDTO(task);
    }

    @Override
    public void changeTaskStatus(UUID taskId, Status newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(newStatus);
        taskRepository.save(task);
    }

    @Override
    public void changeTaskPriority(UUID taskId, Priority newPriority) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setPriority(newPriority);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }

    @Override
    public void addAdminComment(UUID taskId, String commentText, Long adminId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Comment comment = new Comment(task, admin, commentText);
        task.getComments().add(comment);
        taskRepository.save(task);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByFilters(UserFilterDTO filter) {
        return userRepository.findUsersByFilters(filter);
    }

    @Override
    public void updateUserRole(Long userId, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            RoleType newRole = RoleType.valueOf(role.toUpperCase());
            user.setRoleForAdmin(newRole); // Используем метод, который доступен только админам
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role type");
        }
    }

    @Override
    public void toggleUserActivation(Long userId, boolean isActive, String adminName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(isActive);
        userRepository.save(user);

        // Логируем действия администратора
        System.out.println("Admin " + adminName + " changed user " + userId + " activation status to " + isActive);
    }

    @Override
    public void reassignTask(UUID taskId, Long newUserId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User newUser = userRepository.findById(newUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setAssignedUser(newUser);
        taskRepository.save(task);
    }
}