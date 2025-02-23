package com.taskmanagement.service.user;

import com.taskmanagement.dto.user.UserFilterDTO;
import com.taskmanagement.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user); // Регистрация пользователя
    Optional<User> getUserByEmail(String email); // Получение пользователя по email
    Optional<User> getUserById(Long userId); // Получение пользователя по ID
    List<User> getUsersByFilters(UserFilterDTO filter); // Фильтрация пользователей
    void updatePassword(Long userId, String newPassword); // Смена пароля
    void toggleAccountStatus(Long userId); // Временная приостановка/активация аккаунта пользователем
    void confirmEmail(Long userId); // Подтверждение email
    void deleteAccount(Long userId); // Soft Delete (пользователь сам удаляет аккаунт)
}