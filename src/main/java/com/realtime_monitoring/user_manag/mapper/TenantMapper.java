package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.tenant.TenantRequest;
import com.realtime_monitoring.user_manag.dto.tenant.TenantResponse;
import com.realtime_monitoring.user_manag.model.Tenant;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface TenantMapper {
    
    Tenant toEntity(TenantRequest request);
    
    
    TenantResponse toResponse(Tenant tenant);
    
    Tenant updateEntityFromRequest(TenantRequest request, Tenant tenant);
}
