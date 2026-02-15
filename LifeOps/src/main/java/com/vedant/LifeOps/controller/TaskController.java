package com.vedant.LifeOps.controller;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import com.vedant.LifeOps.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task Create(@Valid @RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/status/{status}")
    public List<Task> getByStatus(@PathVariable Status status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }






}
