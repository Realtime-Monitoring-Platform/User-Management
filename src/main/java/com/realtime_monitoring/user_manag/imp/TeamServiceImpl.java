package com.realtime_monitoring.user_manag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.user_manag.dto.team.TeamRequest;
import com.realtime_monitoring.user_manag.dto.team.TeamResponse;
import com.realtime_monitoring.user_manag.dto.team.UpdateTeamRequest;
import com.realtime_monitoring.user_manag.mapper.TeamMapper;
import com.realtime_monitoring.user_manag.mapper.UserMapper;
import com.realtime_monitoring.user_manag.model.Team;
import com.realtime_monitoring.user_manag.model.Tenant;
import com.realtime_monitoring.user_manag.repository.TeamRepository;
import com.realtime_monitoring.user_manag.repository.TenantRepository;
import com.realtime_monitoring.user_manag.service.TeamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TenantRepository tenantRepository;
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public Page<TeamResponse> getAllTeams(Pageable pageable) {
        return this.teamRepository.findAll(pageable).map(teamMapper::toResponse);
    }

    @Override
    public TeamResponse createTeam(TeamRequest request) {
        Team team = teamMapper.toEntity(request);
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        team.setTenant(tenant);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toResponse(savedTeam);
    }

    @Override
    public TeamResponse updateTeam(UUID teamId, UpdateTeamRequest request) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
        teamMapper.updateEntityFromRequest(request, team );
        System.out.println("Updated team::::::::::::::::::::::::::::::::::::::::" + team);
        if (request.getTenantId() != null) {
            Tenant tenant = tenantRepository.findById(request.getTenantId()).orElseThrow(() -> new RuntimeException("Tenant not found"));
            team.setTenant(tenant);
        }

        Team updatedTeam = teamRepository.save(team);
        return teamMapper.toResponse(updatedTeam);
    }


    @Override
    public void deleteTeam(UUID teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new RuntimeException("Team not found");
        }
        teamRepository.deleteById(teamId);
    }

}
