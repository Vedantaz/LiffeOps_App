package com.vedant.LifeOps.dto;

import java.time.LocalDate;

public class GoalRequest {

    private String title;
    private String description;
    private int targetValue;

    private LocalDate targetDate;

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


    // setter value


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
