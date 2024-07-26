package com.cloudNative._4.FinalProject.serviceImpl;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements com.cloudNative._4.FinalProject.service.GreetingService {
    @Override
    public String getGreeting(String message) {
        if ("Hello!".equals(message)) {
            return "Hi!";
        } else {
            return "Hello!";
        }
    }
}
