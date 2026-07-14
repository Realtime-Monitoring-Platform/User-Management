package com.realtime_monitoring.user_manag.service;


import java.util.List;
import java.util.UUID;

import com.realtime_monitoring.user_manag.dto.tenant.TenantRequest;
import com.realtime_monitoring.user_manag.dto.tenant.TenantResponse;

public interface TenantService {

    List<TenantResponse> getAllTenants();
    TenantResponse createTenant(TenantRequest TenantRequest);
    TenantResponse updateTenant(UUID TenantId, TenantRequest TenantRequest);
    void deleteTenant(UUID TenantId);
    TenantResponse getTenantById(UUID TenantId);
    
}
