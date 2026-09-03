package com.realtime_monitoring.usermanag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.usermanag.dto.user.UpdateUserRequest;
import com.realtime_monitoring.usermanag.dto.user.UserRequest;
import com.realtime_monitoring.usermanag.dto.user.UserResponse;
import com.realtime_monitoring.usermanag.dto.user.UserWithTenantResponse;
import com.realtime_monitoring.usermanag.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
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
                        @PageableDefault(page = 0, size = 10, sort = "createdAt") Pageable pageable,
                        HttpServletRequest request) {
                System.out.println("X-User-Id: " + request.getHeader("X-User-Id"));
                System.out.println("X-User-Email: " + request.getHeader("X-User-Email"));
                System.out.println("X-User-Role: " + request.getHeader("X-User-Role"));
                System.out.println("X-User-Tenant-Id: " + request.getHeader("X-User-Tenant-Id"));
                System.out.println("X-User-Name: " + request.getHeader("X-User-Name"));
                return ResponseEntity.ok(userService.getAllUsers(pageable));
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
                return ResponseEntity.ok(userService.getUserById(id));
        }

        @GetMapping("/me")
        public ResponseEntity<UserResponse> getMyProfile(HttpServletRequest request) {
                String userId = request.getHeader("X-User-Id");
                return ResponseEntity.ok(userService.getUserById(UUID.fromString(userId)));
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

        @PutMapping("/updateMyProfile")
        public ResponseEntity<UserResponse> updateMyProfile(@RequestBody UpdateUserRequest request,
                        HttpServletRequest httpRequest) {
                System.out.println("X-User-Id: " + httpRequest.getHeader("X-User-Id"));
                return ResponseEntity.ok(userService.updateMyProfile(request, httpRequest.getHeader("X-User-Id")));
        }

        @PatchMapping("/{id}/status")
        public ResponseEntity<UserResponse> updateUserStatus(@PathVariable UUID id, @RequestParam boolean enabled) {
                return ResponseEntity.ok(userService.updateStatus(id, enabled));
        }

}
