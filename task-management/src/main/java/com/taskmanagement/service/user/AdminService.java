package com.taskmanagement.service.user;

import com.taskmanagement.dto.user.UserFilterDTO;
import com.taskmanagement.model.User;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<User> getUserById(Long userId);
    List<User> getAllUsers();
    List<User> getUsersByFilters(UserFilterDTO filter);
    void updateUserRole(Long userId, String role);
    void toggleUserActivation(Long userId, boolean isActive, String adminName);
}
