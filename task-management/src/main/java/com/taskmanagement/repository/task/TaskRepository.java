package com.taskmanagement.repository.task;

import com.taskmanagement.dto.task.TaskFilterDTO;
import com.taskmanagement.model.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Page<Task> findByAssignedUserId(Long userId, Pageable pageable);
    Page<Task> findTasksByFilters(TaskFilterDTO filter, Pageable pageable);
}