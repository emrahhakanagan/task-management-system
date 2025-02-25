package com.taskmanagement.controller;

import com.taskmanagement.dto.task.TaskDTO;
import com.taskmanagement.dto.task.TaskFilterDTO;
import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;
import com.taskmanagement.service.task.TaskServiceImpl;
import com.taskmanagement.service.user.AdminService;
import com.taskmanagement.service.user.AdminServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskServiceImpl taskService;
    private final AdminServiceImpl adminService;

    public TaskController(TaskServiceImpl taskService, AdminServiceImpl adminService) {
        this.taskService = taskService;
        this.adminService = adminService;
    }


    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/tasks")
    public ResponseEntity<Page<TaskDTO>> getFilteredTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        TaskFilterDTO filter = new TaskFilterDTO();
        filter.setTitle(title);
        filter.setStatus(status);
        filter.setPriority(priority);

        return ResponseEntity.ok(taskService.getTasksByFilters(filter, pageable));
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/tasks")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(adminService.createTask(taskDTO));
    }

    @PutMapping("/admin/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable UUID id,
            @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(adminService.updateTask(id, taskDTO));
    }

    @PatchMapping("/admin/tasks/{id}/status")
    public ResponseEntity<Void> changeTaskStatus(@PathVariable UUID id, @RequestParam Status status) {
        adminService.changeTaskStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/admin/tasks/{id}/priority")
    public ResponseEntity<Void> changeTaskPriority(@PathVariable UUID id, @RequestParam Priority priority) {
        adminService.changeTaskPriority(id, priority);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        adminService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/admin/tasks/{id}/comment")
    public ResponseEntity<Void> addAdminComment(
            @PathVariable UUID id,
            @RequestParam String text,
            @RequestParam Long adminId) {
        adminService.addAdminComment(id, text, adminId);
        return ResponseEntity.noContent().build();
    }

}