package com.vedant.LifeOps.service;

import com.vedant.LifeOps.dto.DashBoardResponse;
import com.vedant.LifeOps.model.Status;
import com.vedant.LifeOps.model.User;
import com.vedant.LifeOps.repo.GoalRepo;
import com.vedant.LifeOps.repo.TaskRepo;
import com.vedant.LifeOps.repo.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;

public class DashBoardService {

    private final GoalRepo goalRepo;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;


    public DashBoardService(GoalRepo goalRepo, TaskRepo taskRepo, UserRepo userRepo) {
        this.goalRepo = goalRepo;
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;


    }

    public DashBoardResponse getDashboard(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username).orElseThrow();

        long userId = user.getId;

        // GOALS
        long totalGoals = goalRepo.countByUserId(userId);
        long completedGoals = goalRepo.countByUserIdAndCurrentValuesGreaterThanEqual(userId, 100);

        long inProgressGoals = totalGoals - completedGoals;

        double completionRate = totalGoals == 0? 0: ((double)completedGoals / totalGoals) *100;

        // tasks

        long totalTasks = taskRepo.countByUserId(userId);

        long completedTasks = taskRepo.countByUserIdAndStatus(userId, Status.DONE);
        long pendingTasks = totalTasks - completedTasks;

        return new DashBoardResponse(

                // goals

                totalGoals,
                completedGoals,
                inProgressGoals,
                completionRate,

                // tasks
                totalTasks,
                completedTasks,
                pendingTasks
        );
    }
}
