package com.vedant.LifeOps.dto;

import com.vedant.LifeOps.model.Priority;
import com.vedant.LifeOps.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDto {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Priority priority;

    @NotNull
    private Status status;

    private LocalDate dueDate;

    public void setCreatedAt(LocalDate now) {
    }
}