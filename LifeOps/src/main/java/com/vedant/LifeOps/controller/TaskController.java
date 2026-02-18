package com.vedant.LifeOps.controller;
import com.vedant.LifeOps.dto.TaskDto;
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
    public ResponseEntity<TaskDto> Create(@Valid @RequestBody Task task){
        // optimized and single eline code
        log.info("Creating task with title: {}", task.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        log.info("Fetching all tasks");
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        if(status != null){
            return ResponseEntity.ok(taskService.getTasksByStatusPaginated(status, page, size, sortBy));
        }

        return ResponseEntity.ok(taskService.getAllTasksPaginated(page, size, sortBy));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        log.info("Fetching task with id: {}", id);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    @GetMapping("/status/{status}")
    public Page<TaskDto> getByStatus(@PathVariable Status status, Pageable pageable) {

        log.info("Fetching tasks with status: {}", status);
        return taskService.getTasksByStatus(status, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Long id, @Valid @RequestBody Task task) {
        log.info("Updating task with id: {}", id);
        TaskDto updated = taskService.updateTask(id, task);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> delete(@PathVariable Long id) {
        log.info("Deleting task with id: {}", id);
        TaskDto deletedTask = taskService.deleteTask(id);

        return ResponseEntity.ok(deletedTask);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<TaskDto>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
            @RequestParam(defaultValue = "0") String sortBy ){

        log.info("Fetching paginated tasks - page :{}, size: {}, sortBy: {}", page, size, sortBy);
        return ResponseEntity.ok(taskService.getAllTasksPaginated(page, size, sortBy));
    }

}
