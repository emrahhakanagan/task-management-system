package com.taskmanagement.repository.user;

import com.taskmanagement.dto.user.UserFilterDTO;
import com.taskmanagement.model.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findUsersByFilters(UserFilterDTO filter);
}
