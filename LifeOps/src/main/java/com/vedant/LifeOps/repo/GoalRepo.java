package com.vedant.LifeOps.repo;

import com.vedant.LifeOps.model.Goal;
import com.vedant.LifeOps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepo extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);
}
