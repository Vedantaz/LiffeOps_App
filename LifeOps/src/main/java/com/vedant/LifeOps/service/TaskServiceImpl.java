package com.vedant.LifeOps.service;

import com.vedant.LifeOps.dto.TaskDto;
import com.vedant.LifeOps.exception.ResourceNotFoundException;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import com.vedant.LifeOps.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

// pagination
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service

public class TaskServiceImpl implements TaskService {

    private TaskDto mapToDto(Task task){
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    @Override
    public TaskDto createTask(Task task) {
        task.setCreatedAt(LocalDate.now());
        Task savedTask = taskRepo.save(task);
        return mapToDto(savedTask);
    }


    @Override
    public Page<TaskDto> getTasks(Status status, int page, int size, String sortBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        Page<Task> taskPage;

        if(status != null){
            taskPage = taskRepo.findByStatus(status, pageable);

        }else{
            taskPage = taskRepo.findAll(pageable);
        }
        return taskPage.map(this::mapToDto);
    }

    @Override
    public TaskDto getTaskById(long id) {

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return mapToDto(task);
    }


    @Override
    public TaskDto updateTask(Long id, Task task) {
        Task existing  = taskRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        existing.setTitle(task.getTitle());
//        log.info("Creating new task with title: {}", task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setPriority(task.getPriority());
        existing.setStatus(task.getStatus());
        existing.setDueDate(task.getDueDate());

        Task updated =  taskRepo.save(existing);
        return mapToDto(updated);
    }

    @Override
    public TaskDto deleteTask(Long id) {
        Task existing = taskRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        taskRepo.delete(existing);
        return mapToDto(existing);
    }
}
