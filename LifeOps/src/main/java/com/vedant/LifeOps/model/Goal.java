package com.vedant.LifeOps.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String description;
    private int targetValue;
    private int currentValue=0;
    private LocalDate targetDate;
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Goal(){};

    public Goal(String title, String description, int targetValue, int currentValue, LocalDate targetDate, User user ){

        this.title = title;
        this.description = description;
        this.targetValue = targetValue;
        this.targetDate = targetDate;
        this.user = user;
    }

}
