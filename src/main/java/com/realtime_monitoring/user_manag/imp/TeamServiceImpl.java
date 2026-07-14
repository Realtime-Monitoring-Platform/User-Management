package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.dto.team.TeamRequest;
import com.realtime_monitoring.user_manag.dto.team.TeamResponse;
import com.realtime_monitoring.user_manag.mapper.TeamMapper;
import com.realtime_monitoring.user_manag.mapper.UserMapper;
import com.realtime_monitoring.user_manag.repository.TeamRepository;
import com.realtime_monitoring.user_manag.service.TeamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public List<TeamResponse> getAllTeams() {
        return this.teamRepository.findAll().stream().map(teamMapper::toResponse).toList();

    }

    @Override
    public TeamResponse createTeam(TeamRequest TeamRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTeam'");
    }

    @Override
    public TeamResponse updateTeam(UUID TeamId, TeamRequest TeamRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTeam'");
    }

    @Override
    public void deleteTeam(UUID TeamId) {
        this.teamRepository.deleteById(TeamId);
    }

}
