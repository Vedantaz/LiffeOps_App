package com.vedant.LifeOps.dto;

public class AuthResponse {

    private String message;

    private String accessToken;
    private String refreshToken;

    public AuthResponse(String message, String accessToken, String refreshToken) {
        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}