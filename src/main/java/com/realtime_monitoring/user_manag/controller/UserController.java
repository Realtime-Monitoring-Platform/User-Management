package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.dto.user.UserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserResponse;
import com.realtime_monitoring.user_manag.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/")
    public List<UserResponse> getAllUsers() {
        return this.userService.getAllUsers();
    }
    
    @PostMapping
    public UserResponse CreateUser(@RequestBody UserRequest entity) {
        
        return this.userService.createUser(entity);
    }
    
    
}
