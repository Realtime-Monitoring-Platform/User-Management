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

    @Mapping(target = "teamLeaderId", ignore = true)
    Team toEntity(TeamRequest request);

    @Mapping(target = "teamLeaderId", source = "teamLeaderId.id")
    TeamResponse toResponse(Team team);

    @Mapping(target = "teamLeaderId", ignore = true)
    void updateEntityFromRequest(UpdateTeamRequest request,
                                 @MappingTarget Team team);
}