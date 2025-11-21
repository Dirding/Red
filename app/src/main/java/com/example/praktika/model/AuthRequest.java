package com.example.praktika.model;

public class AuthRequest {
    public String email;
    public String password;

    public AuthRequest(String login, String password) {
        this.email = login + "@transport.app";
        this.password = password;
    }
}