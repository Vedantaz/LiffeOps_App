package com.vedant.LifeOps.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String description;
    @Setter
    @Getter
    private int targetValue;

    @Setter
    @Getter
    private int currentValue=0;
    @Getter
    @Setter
    private LocalDate targetDate;
    private boolean completed = false;

    @Setter
    @Getter

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Goal(String title, String description, int targetValue, int currentValue, LocalDate targetDate, User user ){

        this.title = title;
        this.description = description;
        this.targetValue = targetValue;
        this.targetDate = targetDate;
        this.user = user;
    }


    public Goal(String title, String description, int targetValue, LocalDate targetDate, User user) {
    }

    public void IsCompleted(boolean completed){
    }

}

