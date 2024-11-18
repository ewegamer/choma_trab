package com.example.authentication.domain;

public record RegisterDTO(String login, String password, UserRole role) {
}