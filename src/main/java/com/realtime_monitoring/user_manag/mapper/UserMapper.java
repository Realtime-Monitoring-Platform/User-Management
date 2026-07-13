package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.user.CreateUserRequest;
import com.realtime_monitoring.user_manag.dto.user.ResponseUserRequest;
import com.realtime_monitoring.user_manag.model.User;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface UserMapper {
    
    User toEntity(CreateUserRequest request);
    
    ResponseUserRequest toResponse(User user);
    User updateEntityFromRequest(CreateUserRequest request, User user);
}
