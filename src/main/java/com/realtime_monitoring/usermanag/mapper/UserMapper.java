package com.realtime_monitoring.usermanag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.usermanag.dto.user.UpdateUserRequest;
import com.realtime_monitoring.usermanag.dto.user.UserRequest;
import com.realtime_monitoring.usermanag.dto.user.UserResponse;
import com.realtime_monitoring.usermanag.model.User;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)

public interface UserMapper {
    User toEntity(UserRequest request);
    
    UserResponse toResponse(User user);

    void updateEntityFromRequest(UpdateUserRequest request, @MappingTarget User user);
}
