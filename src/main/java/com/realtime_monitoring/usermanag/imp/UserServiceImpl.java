package com.realtime_monitoring.usermanag.imp;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realtime_monitoring.usermanag.dto.tenant.TenantDto;
import com.realtime_monitoring.usermanag.dto.user.UpdateUserRequest;
import com.realtime_monitoring.usermanag.dto.user.UserRequest;
import com.realtime_monitoring.usermanag.dto.user.UserResponse;
import com.realtime_monitoring.usermanag.dto.user.UserWithTenantResponse;

import com.realtime_monitoring.usermanag.kafka.UserProducer;
import com.realtime_monitoring.usermanag.mapper.UserMapper;
import com.realtime_monitoring.usermanag.model.User;
import com.realtime_monitoring.usermanag.model.UserStatus;
import com.realtime_monitoring.usermanag.repository.UserRepo;

import com.realtime_monitoring.usermanag.service.UserService;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;

    private final UserMapper userMapper;

  
    private final UserProducer userProducer;
    // private final TenantGrpcClient tenantClient;
    
    @Override
    public UserResponse createUser(UserRequest request) {
        validateEmail(request.getEmail());
        validateUsername(request.getUsername());
        User user = userMapper.toEntity(request);
        // user.setTenant(getTenant(request.getTenantId()).orElse(null));
        user.setStatus(UserStatus.ACTIVE);

        
        User savedUser= userRepository.save(user);
        userProducer.sendUserCreation(savedUser);
        return userMapper.toResponse(savedUser);
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
        // User user = findById(id);
        // if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
        //     validateEmail(request.getEmail());
        // }

        // if (request.getUsername() != null && !request.getUsername().equals(user.getUsername())) {
        //     validateUsername(request.getUsername());
        // }

        // userMapper.updateEntityFromRequest(request, user);

        // // if (request.getTenantId() != null) {
        // // user.setTenant(getTenant(request.getTenantId()).orElse(null));
        // // }

        

        // User updatedUser = userRepository.save(user);
        // userProducer.sendUserUpdate(updatedUser);
        // return userMapper.toResponse(updatedUser);

        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("user not found with id: " + id);
        }
        User user = userOptional.get();
        userMapper.updateEntityFromRequest(request, user);
        User updatedTenant = userRepository.save(user);
        userProducer.sendUserUpdate(updatedTenant);
        return userMapper.toResponse(updatedTenant);
    }

    @Override
    public void delete(UUID id) {
        User user = findById(id);
        user.setStatus(UserStatus.INACTIVE);
        userProducer.sendUserDeleted(user.getId());
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