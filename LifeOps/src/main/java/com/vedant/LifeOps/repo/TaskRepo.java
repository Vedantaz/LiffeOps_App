package com.vedant.LifeOps.repo;
import com.vedant.LifeOps.model.Priority;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);
}
