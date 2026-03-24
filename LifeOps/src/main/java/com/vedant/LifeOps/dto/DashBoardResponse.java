package com.vedant.LifeOps.dto;

public class DashBoardResponse {
    
    private long totalGoals;
    private long completedGoals;
    private long inProgressGoals;
    private double completionRate;
    
    private long totalTasks;
    private long completedTasks;
    private long pendingTasks;

    // Constrcutor

    public DashBoardResponse(long totalGoals, long completedGoals, long inProgressGoals, double completionRate, 
    long totalTasks, long pendingTasks, long completedTasks){
        this.totalGoals = totalGoals;
        this.completedGoals = completedGoals;
        this.inProgressGoals = inProgressGoals;
        this.completionRate = completionRate;
        this.totalTasks = totalTasks;
        this.pendingTasks = pendingTasks;
        this.completedTasks = completedTasks;

    }

    // getter methods


    public long getTotalGoals() {
        return totalGoals;
    }

    public long getCompletedGoals() {
        return completedGoals;
    }

    public long getInProgressGoals() {
        return inProgressGoals;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }

    public long getPendingTasks() {
        return pendingTasks;
    }
}
