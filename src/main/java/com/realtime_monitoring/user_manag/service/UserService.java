package com.realtime_monitoring.user_manag.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realtime_monitoring.user_manag.dto.user.UpdateUserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserResponse;

public interface UserService {
    
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(UUID userId);
    Page<UserResponse> getAllUsers(Pageable pageable);
    UserResponse update(UUID id, UpdateUserRequest request);
    void delete(UUID id);
    List<UserResponse> findByTenant(UUID tenantId);

}
