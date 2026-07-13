package com.realtime_monitoring.user_manag.service;


import com.realtime_monitoring.user_manag.dto.tenant.TenantRequest;
import com.realtime_monitoring.user_manag.dto.tenant.TenantResponse;

public interface TenantService {

    TenantResponse getAllTenants();
    TenantResponse createTenant(TenantRequest TenantRequest);
    TenantResponse updateTenant(Long TenantId, TenantRequest TenantRequest);
    void deleteTenant(Long TenantId);
    
}
