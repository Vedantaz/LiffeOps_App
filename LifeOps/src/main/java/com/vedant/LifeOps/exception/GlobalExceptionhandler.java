package com.vedant.LifeOps.exception;

import com.vedant.LifeOps.dto.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleUserNotFound(UsernameNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiResponseError(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseError> handleBadCredentials(BadCredentialsException ex) {
        return new ResponseEntity<>(
                new ApiResponseError("Invalid username or password", 401),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponseError> handleExpiredJwt(ExpiredJwtException ex) {
        return new ResponseEntity<>(
                new ApiResponseError("JWT Token Expired", 401),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponseError> handleJwtException(JwtException ex) {
        return new ResponseEntity<>(
                new ApiResponseError("Invalid JWT Token", 401),
                HttpStatus.UNAUTHORIZED
        );
    }

    // handle resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex){

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .msg(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException Ex){
        return ResponseEntity.badRequest().body(Ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error-> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneral(Exception ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

}

