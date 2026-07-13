package com.realtime_monitoring.user_manag.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.realtime_monitoring.user_manag.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    
    Optional<Team> findByNameAndTenantId(String name, UUID tenantId);
    

    List<Team> findByTenantId(UUID tenantId);
    
    
    boolean existsByNameAndTenantId(String name, UUID tenantId);
}
