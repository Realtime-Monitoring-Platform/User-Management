package com.realtime_monitoring.usermanag.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.realtime_monitoring.usermanag.dto.team.TeamResponse;
import com.realtime_monitoring.usermanag.dto.team.TeamRequest;
import com.realtime_monitoring.usermanag.dto.team.UpdateTeamRequest;
import com.realtime_monitoring.usermanag.model.Team;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface TeamMapper {
    
    Team toEntity(TeamRequest request);
    
    TeamResponse toResponse(Team team);
        
    void updateEntityFromRequest(UpdateTeamRequest request, @MappingTarget Team team);
}
