package com.vedant.LifeOps.service;

import com.vedant.LifeOps.dto.TaskDto;
import com.vedant.LifeOps.dto.TaskRequestDto;
import com.vedant.LifeOps.model.SortDirection;
import com.vedant.LifeOps.model.Status;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface TaskService {

    TaskDto createTask(@Valid TaskRequestDto task);

//    List<TaskDto> getAllTasks();

    TaskDto getTaskById(long id);

    TaskDto updateTask(Long id, @Valid TaskRequestDto task);

    TaskDto deleteTask(Long id);

    Page<TaskDto> getTasks(Status status, int page, int size, SortDirection direction, String sortBy);
}
