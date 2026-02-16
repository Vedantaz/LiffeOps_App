package com.vedant.LifeOps.controller;
import com.vedant.LifeOps.dto.TaskDto;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import com.vedant.LifeOps.service.TaskService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tasks")
@Slf4j

public class TaskController {

    private final TaskService taskService;

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

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id) {
        log.info("Fetching task with id: {}", id);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    @GetMapping("/status/{status}")
    public List<TaskDto> getByStatus(@PathVariable Status status) {

        log.info("Fetching tasks with status: {}", status);
        return taskService.getTasksByStatus(status);
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

}
