package com.taskmanagement.service.user;

import com.taskmanagement.dto.user.UserFilterDTO;
import com.taskmanagement.enums.RoleType;
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