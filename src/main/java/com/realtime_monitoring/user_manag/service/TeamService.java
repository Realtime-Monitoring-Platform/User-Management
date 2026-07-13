package com.realtime_monitoring.user_manag.service;

import com.realtime_monitoring.user_manag.dto.team.TeamRequest;
import com.realtime_monitoring.user_manag.dto.team.TeamResponse;

public interface TeamService {

    TeamResponse getAllTeams();
    TeamResponse createTeam(TeamRequest TeamRequest);
    TeamResponse updateTeam(Long TeamId, TeamRequest TeamRequest);
    void deleteTeam(Long TeamId);
    
}
