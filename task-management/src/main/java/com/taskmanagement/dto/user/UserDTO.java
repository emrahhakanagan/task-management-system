package com.taskmanagement.dto.user;

import com.taskmanagement.enums.RoleType;
import com.taskmanagement.model.User;

import java.time.LocalDateTime;

public class UserDTO {
    private Long id;
    private String email;
    private RoleType role;
    private boolean isActive;
    private boolean isEmailConfirmed;
    private boolean toggleAccountStatus;
    private boolean isDeleted;
    private LocalDateTime createdAt;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.isActive = user.isActive();
        this.isEmailConfirmed = user.isEmailConfirmed();
        this.toggleAccountStatus = user.isToggleAccountStatus();
        this.isDeleted = user.isDeleted();
        this.createdAt = user.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    public boolean isToggleAccountStatus() {
        return toggleAccountStatus;
    }

    public void setToggleAccountStatus(boolean toggleAccountStatus) {
        this.toggleAccountStatus = toggleAccountStatus;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}