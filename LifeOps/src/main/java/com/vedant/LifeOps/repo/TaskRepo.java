package com.vedant.LifeOps.repo;
import com.vedant.LifeOps.model.Priority;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    Page<Task> findByStatus(Status status, Pageable pageable);
    List<Task> findByPriority(Priority priority);
    List<Task> findByStatus(Status status);


}
