package com.realtime_monitoring.user_manag.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.realtime_monitoring.user_manag.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission , UUID> {
    

    Optional<Permission> findByName(String name);
    Optional<Permission> findById(UUID id);
    List<Permission> findByResource(String resource);

    List<Permission> findByResourceAndAction(String resource, String action);
    
    
}
