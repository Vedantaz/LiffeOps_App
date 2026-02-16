package com.vedant.LifeOps.service;

import com.vedant.LifeOps.dto.TaskDto;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TaskService {

    TaskDto createTask(Task task);

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(long id);


    TaskDto updateTask(Long id, Task task);

    TaskDto deleteTask(Long id);

    List<TaskDto> getTasksByStatus(Status status);

}
