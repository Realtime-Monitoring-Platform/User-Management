package com.realtime_monitoring.user_manag.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtime_monitoring.user_manag.model.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    
}
