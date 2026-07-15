package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.user.UpdateUserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserRequest;
import com.realtime_monitoring.user_manag.dto.user.UserResponse;
import com.realtime_monitoring.user_manag.model.User;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface UserMapper {
    
    User toEntity(UserRequest request);
    
    @Mapping(target = "tenantId", source = "tenant.id")
   
    UserResponse toResponse(User user);

    @Mapping(target = "tenant", ignore = true)
    void updateEntityFromRequest(UpdateUserRequest request, @MappingTarget User user);
}
