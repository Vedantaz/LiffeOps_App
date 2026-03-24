package com.vedant.LifeOps.controller;

import com.vedant.LifeOps.dto.GoalRequest;
import com.vedant.LifeOps.dto.ProgressRequest;
import com.vedant.LifeOps.model.Goal;
import com.vedant.LifeOps.service.GoalService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService){
        this.goalService = goalService;

    }

    @PostMapping
    public Goal createGoal(@RequestBody GoalRequest request){
        return goalService.createGoal(request);

    }

    @GetMapping
    public List<Goal> getGoals(){
        return goalService.getUserGoals();

    }

    @PatchMapping("/{id}/progress")
    public ResponseEntity<Goal> updateProgress(
            @PathVariable Long id,
            @RequestBody ProgressRequest progressRequest
            ){
        return ResponseEntity.ok(goalService.updateProgress(id, progressRequest.getValue()));
    }





}
