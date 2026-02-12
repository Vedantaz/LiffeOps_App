package com.vedant.LifeOps.service;

import com.vedant.LifeOps.model.Task;
import com.vedant.LifeOps.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskRepo.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepo.findById(id).orElseThrow(()-> new RuntimeException("Task not found.") );

    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existing  = getTaskById(id);

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setPriority(updatedTask.getPriority());
        existing.setStatus(updatedTask.getStatus());
        existing.setDueDate(updatedTask.getDueDate());

        return taskRepo.save(existing);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }


}
