package com.vedant.LifeOps.dto;

import com.vedant.LifeOps.model.Priority;
import com.vedant.LifeOps.model.Status;
import lombok.Data;
import java.time.LocalDate;


public class TaskDto {
    private long id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate dueDate;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // setter methods


    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
