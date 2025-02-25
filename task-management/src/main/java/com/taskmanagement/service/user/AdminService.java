package com.taskmanagement.service.user;

import com.taskmanagement.dto.task.TaskDTO;
import com.taskmanagement.dto.user.UserFilterDTO;
import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;
import com.taskmanagement.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminService {
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO updateTask(UUID taskId, TaskDTO taskDTO);
    void changeTaskStatus(UUID taskId, Status newStatus);
    void changeTaskPriority(UUID taskId, Priority newPriority);
    void deleteTask(UUID taskId);
    void addAdminComment(UUID taskId, String commentText, Long adminId);
    Optional<User> getUserById(Long userId);
    List<User> getAllUsers();
    List<User> getUsersByFilters(UserFilterDTO filter);
    void updateUserRole(Long userId, String role);
    void toggleUserActivation(Long userId, boolean isActive, String adminName);
    void reassignTask(UUID taskId, Long newUserId);
}
