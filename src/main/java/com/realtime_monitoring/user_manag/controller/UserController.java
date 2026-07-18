package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.dto.user.UpdateUserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserResponse;
import com.realtime_monitoring.user_manag.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;

        @GetMapping
        public ResponseEntity<Page<UserResponse>> getAllUsers(

                @PageableDefault(page = 0, size = 10, sort = "createdAt") Pageable pageable) {
                return ResponseEntity.ok(userService.getAllUsers(pageable));
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
                return ResponseEntity.ok(userService.getUserById(id));
        }

        @PostMapping
        public ResponseEntity<UserResponse> createUser(
                @RequestBody UserRequest request) {
                UserResponse response = userService.createUser(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequest request) {
                return ResponseEntity.ok(userService.update(id, request));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
                userService.delete(id);
                return ResponseEntity.noContent().build();
        }

        @PatchMapping("/{id}/status")
        public ResponseEntity<UserResponse> updateUserStatus(@PathVariable UUID id,@RequestParam boolean enabled) {
                return ResponseEntity.ok(userService.updateStatus(id, enabled));
        }

}
