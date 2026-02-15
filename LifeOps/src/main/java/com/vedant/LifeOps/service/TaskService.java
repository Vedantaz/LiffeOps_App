package com.vedant.LifeOps.service;

import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TaskService {

    Task createTask(Task task);

    List<Task> getAllTasks();

    Task getTaskById(long id);


    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    List<Task> getTasksByStatus(Status status);
}
