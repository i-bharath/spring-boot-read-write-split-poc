package com.example.demo.responses;

public class LoginResponse {
    private String token;



    private long expiresIn;

    public String getToken() {
        return token;
    }

    public String setToken(String token){
        this.token = token;
        return token;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}