package com.vedant.LifeOps.service;

import com.vedant.LifeOps.dto.TaskDto;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;

@Service
public interface TaskService {

    TaskDto createTask(Task task);

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(long id);

    TaskDto updateTask(Long id, Task task);

    TaskDto deleteTask(Long id);

    Page<TaskDto> getTasksByStatus(Status status, Pageable pageable);

    Page<TaskDto> getTasksByStatusPaginated(
            Status status,
            int page,
            int size,
            String sortBy
    );
    // pagination


    Page<TaskDto> getAllTasksPaginated(int page, int size, String sortBy);


}
