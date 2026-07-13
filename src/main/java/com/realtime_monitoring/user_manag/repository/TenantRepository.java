package com.realtime_monitoring.user_manag.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtime_monitoring.user_manag.model.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    
    Optional<Tenant> findByName(String name);
    
    
    Optional<Tenant> findByDomain(String domain);
    

    boolean existsByName(String name);
    
    boolean existsByDomain(String domain);
}
