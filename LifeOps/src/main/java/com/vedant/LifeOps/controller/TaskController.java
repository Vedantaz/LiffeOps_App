package com.vedant.LifeOps.controller;
import com.vedant.LifeOps.dto.ApiResponse;
import com.vedant.LifeOps.dto.TaskDto;
import com.vedant.LifeOps.dto.TaskRequestDto;
import com.vedant.LifeOps.model.SortDirection;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import com.vedant.LifeOps.service.TaskService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/tasks")
@Tag(name = "Task APIs", description = "Operations related to task management")

public class TaskController {

    private final TaskService taskService;

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskDto>> Create(@Valid @RequestBody TaskRequestDto task){
        // optimized and single eline code
        log.info("Creating task with title: {}", task.getTitle());
        TaskDto created = taskService.createTask(task);
        ApiResponse<TaskDto> response = ApiResponse.<TaskDto>builder()
                .success(true)
                .msg("Task created successfully!")
                .data(created)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<TaskDto>> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC")SortDirection direction,
            @RequestParam(defaultValue = "id") String sortBy
    ){

        log.info("Fetching tasks - status: {}, page: {}, size: {}, sortBy: {}",
                status, page, size, sortBy);

        return ResponseEntity.ok(taskService.getTasks(status, page, size, direction, sortBy));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> getById(@PathVariable Long id) {
        log.info("Fetching task with id: {}", id);
        TaskDto task = taskService.getTaskById(id);

        ApiResponse<TaskDto> response = ApiResponse.<TaskDto>builder()
                .success(true)
                .msg("Task fetched successfully")
                .data(task)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @Valid @RequestBody TaskRequestDto task) {
        log.info("Updating task with id: {}", id);
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDto>> delete(@PathVariable Long id) {


        log.info("Deleting task with id: {}", id);
        TaskDto deletedTask = taskService.deleteTask(id);

        ApiResponse<TaskDto> response = ApiResponse.<TaskDto>builder()
                .success(true)
                .msg("Task deleted successfully")
                .data(deletedTask)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

}
