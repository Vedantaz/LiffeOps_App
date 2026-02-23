package com.vedant.LifeOps.service;

import com.vedant.LifeOps.dto.TaskDto;
import com.vedant.LifeOps.dto.TaskRequestDto;
import com.vedant.LifeOps.exception.ResourceNotFoundException;
import com.vedant.LifeOps.model.SortDirection;
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
    public TaskDto createTask(TaskRequestDto request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setCreatedAt(LocalDate.now());

        Task saved = taskRepo.save(task);
        return mapToDto(saved);
    }


    @Override
    public Page<TaskDto> getTasks(Status status, int page, int size, SortDirection direction, String sortBy){

        Sort.Direction sortDirection = direction == SortDirection.DESC ? Sort.Direction.DESC : Sort.Direction.ASC;


        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
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
    public TaskDto updateTask(Long id, TaskRequestDto task) {
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
