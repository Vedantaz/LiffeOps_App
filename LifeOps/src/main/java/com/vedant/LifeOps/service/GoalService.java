package com.vedant.LifeOps.service;


import com.vedant.LifeOps.dto.GoalRequest;
import com.vedant.LifeOps.model.Goal;
import com.vedant.LifeOps.model.User;
import com.vedant.LifeOps.repo.GoalRepo;
import com.vedant.LifeOps.repo.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private final GoalRepo goalRepo;
    private final UserRepo userRepo;

    public GoalService(GoalRepo goalRepo, UserRepo userRepo){
        this.goalRepo = goalRepo;
        this.userRepo = userRepo;
    }

    public Goal createGoal(GoalRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepo.findByUsername(username).orElseThrow();

        Goal goal = new Goal(
                request.getTitle(),
                request.getDescription(),
                request.getTargetValue(),
                request.getTargetDate(),
                user
        );
        return goalRepo.save(goal);
    }

    public List<Goal> getUserGoals(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepo.findByUsername(username).orElseThrow();

        return goalRepo.findByUser(user);

    }

    public Goal updateProgress(Long goalId, int value){
        Goal goal = goalRepo.findById(goalId).orElseThrow();

        goal.setCurrentValue(goal.getCurrentValue() + value);
        if(goal.getCurrentValue() >= goal.getTargetValue()){
            goal.IsCompleted(true);

        }
        return goalRepo.save(goal);

    }
}
