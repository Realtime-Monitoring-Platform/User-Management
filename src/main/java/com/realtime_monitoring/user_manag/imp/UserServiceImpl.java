package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realtime_monitoring.user_manag.dto.user.UpdateUserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserResponse;
import com.realtime_monitoring.user_manag.mapper.UserMapper;
import com.realtime_monitoring.user_manag.model.User;
import com.realtime_monitoring.user_manag.model.UserStatus;
import com.realtime_monitoring.user_manag.repository.UserRepo;

import com.realtime_monitoring.user_manag.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        validateEmail(request.getEmail());
        validateUsername(request.getUsername());
        User user = userMapper.toEntity(request);
       // user.setTenant(getTenant(request.getTenantId()).orElse(null));
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        return userMapper.toResponse(userRepository.findById(id).orElse(null));

    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public UserResponse update(
            UUID id,
            UpdateUserRequest request) {
        User user = findById(id);
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            validateEmail(request.getEmail());
        }

        if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
            validateUsername(request.getUsername());
        }

        userMapper.updateEntityFromRequest(request, user);

        // if (request.getTenantId() != null) {
        //     user.setTenant(getTenant(request.getTenantId()).orElse(null));
        // }

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void delete(UUID id) {
        User user = findById(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findByTenant(UUID tenantId) {
        return userRepository
                .findByTenantId(tenantId)
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    private User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    

    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("email already exists");
        }
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("username already exists");
        }
    }

    @Override
    public UserResponse updateStatus(UUID id, boolean enabled) {
        User user = findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.setStatus(enabled ? UserStatus.ACTIVE : UserStatus.INACTIVE);
        return userMapper.toResponse(userRepository.save(user));
    }

}