package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.permissions.PermissionRequest;
import com.realtime_monitoring.user_manag.dto.permissions.PermissionResponse;
import com.realtime_monitoring.user_manag.model.Permission;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface PermissionMapper {
    
    Permission toEntity(PermissionRequest request);
    
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "updatedAt", source = "updated_at")
    PermissionResponse toResponse(Permission permission);
    

    void updateEntityFromRequest(PermissionRequest request, @MappingTarget Permission permission);
}
