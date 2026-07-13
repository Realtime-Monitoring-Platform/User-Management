package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.role.RoleRequest;
import com.realtime_monitoring.user_manag.dto.role.RoleResponse;
import com.realtime_monitoring.user_manag.model.Role;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = PermissionMapper.class
)

public interface RoleMapper {
    
    Role toEntity(RoleRequest request);
    
    RoleResponse toResponse(Role role);
    
    Role updateEntityFromRequest(RoleRequest request, Role role);
}
