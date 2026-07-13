package com.realtime_monitoring.user_manag.service;

import java.util.List;
import java.util.UUID;

import com.realtime_monitoring.user_manag.dto.team.TeamRequest;
import com.realtime_monitoring.user_manag.dto.team.TeamResponse;

public interface TeamService {

    List<TeamResponse> getAllTeams();
    TeamResponse createTeam(TeamRequest TeamRequest);
    TeamResponse updateTeam(UUID TeamId, TeamRequest TeamRequest);
    void deleteTeam(UUID TeamId);
    
}
