package com.realtime_monitoring.usermanag.service;

import java.util.List;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realtime_monitoring.usermanag.dto.team.TeamRequest;
import com.realtime_monitoring.usermanag.dto.team.TeamResponse;
import com.realtime_monitoring.usermanag.dto.team.UpdateTeamRequest;

public interface TeamService {

    Page<TeamResponse> getAllTeams(Pageable pageable);
    TeamResponse createTeam(TeamRequest TeamRequest);
    TeamResponse updateTeam(UUID TeamId, UpdateTeamRequest TeamRequest);
    void deleteTeam(UUID TeamId);
    
}
