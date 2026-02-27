package com.vedant.LifeOps.exception;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class ApiResponseError {
    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ApiResponseError(String message, int status){
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;

    }

    public String getMessage(){return message;};
    public int getStatus(){return status;};
    public LocalDateTime getTimestamp(){ return timestamp;}

}
