package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
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
    
    
    TeamResponse toResponse(Team team);
    
    Team updateEntityFromRequest(UpdateTeamRequest request, Team team);
}
