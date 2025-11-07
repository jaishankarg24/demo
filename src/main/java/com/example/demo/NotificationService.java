package com.example.demo;

public interface NotificationService {
    void send(String message);
    void send(String message, String recipientEmail);
}
