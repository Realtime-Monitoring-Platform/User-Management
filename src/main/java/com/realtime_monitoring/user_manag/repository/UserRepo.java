package com.realtime_monitoring.user_manag.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.realtime_monitoring.user_manag.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByTenantId(UUID tenantId);
    
    
    boolean existsByUsername(String username);
    

    boolean existsByEmail(String email);
}
