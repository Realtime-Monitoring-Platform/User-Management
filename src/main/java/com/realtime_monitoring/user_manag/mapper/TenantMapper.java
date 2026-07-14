package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.tenant.TenantResponse;
import com.realtime_monitoring.user_manag.dto.tenant.TenantRequest;
import com.realtime_monitoring.user_manag.dto.tenant.UpdateTenantRequest;
import com.realtime_monitoring.user_manag.model.Tenant;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface TenantMapper {
    
    Tenant toEntity(TenantRequest request);
    
    
    TenantResponse toResponse(Tenant tenant);
    
    void updateEntityFromRequest(UpdateTenantRequest request, @MappingTarget Tenant tenant);
}
