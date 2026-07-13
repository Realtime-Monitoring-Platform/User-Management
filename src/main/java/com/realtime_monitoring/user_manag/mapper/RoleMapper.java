package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
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
    
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "updatedAt", source = "updated_at")
    RoleResponse toResponse(Role role);
    
    void updateEntityFromRequest(RoleRequest request, @MappingTarget Role role);
}
