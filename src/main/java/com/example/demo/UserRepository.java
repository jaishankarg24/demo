package com.example.demo;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}