package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.team.TeamResponse;
import com.realtime_monitoring.user_manag.dto.team.TeamRequest;
import com.realtime_monitoring.user_manag.dto.team.UpdateTeamRequest;
import com.realtime_monitoring.user_manag.model.Team;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface TeamMapper {
    
    Team toEntity(TeamRequest request);
    
    @Mapping(target = "tenantId", source = "tenant.id")
    TeamResponse toResponse(Team team);
        
    @Mapping(target = "tenant", ignore = true)
    void updateEntityFromRequest(UpdateTeamRequest request, @MappingTarget Team team);
}
