package com.taskmanagement.dto.user;

import com.taskmanagement.enums.RoleType;

public class UserFilterDTO {
    private Boolean isActive;
    private Boolean isEmailConfirmed;
    private Boolean isDeleted;
    private RoleType role;

    public UserFilterDTO() {}

    public UserFilterDTO(Boolean isActive, Boolean isEmailConfirmed, Boolean isDeleted, RoleType role) {
        this.isActive = isActive;
        this.isEmailConfirmed = isEmailConfirmed;
        this.isDeleted = isDeleted;
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setIsEmailConfirmed(Boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}