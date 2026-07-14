package com.realtime_monitoring.user_manag.service;


import java.util.List;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realtime_monitoring.user_manag.dto.tenant.TenantRequest;
import com.realtime_monitoring.user_manag.dto.tenant.TenantResponse;

public interface TenantService {

    Page<TenantResponse> getAllTenants(Pageable pageable);
    TenantResponse createTenant(TenantRequest TenantRequest);
    TenantResponse updateTenant(UUID TenantId, TenantRequest TenantRequest);
    void deleteTenant(UUID TenantId);
    TenantResponse getTenantById(UUID TenantId);
    
}
