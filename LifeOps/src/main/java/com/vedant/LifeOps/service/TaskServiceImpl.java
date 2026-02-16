package com.vedant.LifeOps.service;

import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import com.vedant.LifeOps.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDate.now());
        return taskRepo.save(task);
    }

    public Task getTaskByStatus(Status status){
        return (Task) taskRepo.findByStatus(status);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepo.findById(id).orElseThrow(()-> new RuntimeException("Task not found.") );

    }

    public List<Task> getTasksByStatus(Status status) {
        return taskRepo.findByStatus(status);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existing  = getTaskById(id);

        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setPriority(task.getPriority());
        existing.setStatus(task.getStatus());
        existing.setDueDate(task.getDueDate());

        return taskRepo.save(existing);
    }

    @Override
    public Task deleteTask(Long id) {
        Task existing = getTaskById(id);
        taskRepo.delete(existing);
        return existing;
    }
}
