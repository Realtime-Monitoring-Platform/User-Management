package com.realtime_monitoring.user_manag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.user_manag.dto.team.TeamRequest;
import com.realtime_monitoring.user_manag.dto.team.TeamReponse;
import com.realtime_monitoring.user_manag.model.Team;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface TeamMapper {
    
    Team toEntity(TeamRequest request);
    
    
    TeamReponse toResponse(Team team);
    
    Team updateEntityFromRequest(TeamRequest request, Team team);
}
