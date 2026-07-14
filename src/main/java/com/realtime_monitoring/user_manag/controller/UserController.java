package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.dto.user.UserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserResponse;
import com.realtime_monitoring.user_manag.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/")
    public Page<UserResponse> getAllUsers(
        @PageableDefault(
                page = 0,
                size = 10,
                sort = "createdAt"
                //direction = Sort.Direction.DESC
        )
        Pageable pageable
    ) {
        return this.userService.getAllUsers(pageable);
    }
    
    @PostMapping
    public UserResponse CreateUser(@RequestBody UserRequest entity) {
        
        return this.userService.createUser(entity);
    }
    
    
}
