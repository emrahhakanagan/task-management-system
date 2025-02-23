package com.taskmanagement.model;

import com.taskmanagement.enums.RoleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role = RoleType.USER;

    @Column(nullable = false)
    private boolean isActive = false; // Активен ли аккаунт (по умолчанию false)

    @Column(nullable = false)
    private boolean isEmailConfirmed = false; // Подтвержден ли email

    @Column(nullable = false)
    private boolean toggleAccountStatus = false; // Временная приостановка

    @Column(nullable = false)
    private boolean isDeleted = false; // Soft Delete

    @Column(updatable = false)
    private LocalDateTime createdAt; // Дата регистрации

    @Column
    private LocalDateTime updatedAt; // Дата последнего изменения

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = RoleType.USER;
    }

    public User(String email, String password, RoleType role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    // Только Админ имеет права менять роль пользователя
    public void setRoleForAdmin(RoleType role) {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}