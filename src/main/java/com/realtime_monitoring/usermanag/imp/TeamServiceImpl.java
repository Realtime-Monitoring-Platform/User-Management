package com.realtime_monitoring.usermanag.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realtime_monitoring.usermanag.dto.team.TeamRequest;
import com.realtime_monitoring.usermanag.dto.team.TeamResponse;
import com.realtime_monitoring.usermanag.dto.team.UpdateTeamRequest;
import com.realtime_monitoring.usermanag.exception.ResourceNotFoundException;
import com.realtime_monitoring.usermanag.mapper.TeamMapper;
import com.realtime_monitoring.usermanag.mapper.UserMapper;
import com.realtime_monitoring.usermanag.kafka.UserProducer;
import com.realtime_monitoring.usermanag.model.Team;
import com.realtime_monitoring.usermanag.repository.TeamRepository;
import com.realtime_monitoring.usermanag.service.TeamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    //private final TenantRepository tenantRepository;
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserProducer userProducer;

    @Override
    public Page<TeamResponse> getAllTeams(Pageable pageable) {
        return this.teamRepository.findAll(pageable).map(teamMapper::toResponse);
    }

    @Override
    public TeamResponse createTeam(TeamRequest request) {
        Team team = teamMapper.toEntity(request);
        //Tenant tenant = tenantRepository.findById(request.getTenantId())
       // .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
       // team.setTenant(tenant);
        Team savedTeam = teamRepository.save(team);
        userProducer.sendTeamCreation(savedTeam);
        return teamMapper.toResponse(savedTeam);
    }

    @Override
    public TeamResponse updateTeam(UUID teamId, UpdateTeamRequest request) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        teamMapper.updateEntityFromRequest(request, team );
        System.out.println("Updated team: " + team);
        // if (request.getTenantId() != null) {
        //     Tenant tenant = tenantRepository.findById(request.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
        //     team.setTenant(tenant);
        // }

        Team updatedTeam = teamRepository.save(team);
        userProducer.sendTeamUpdate(updatedTeam);
        return teamMapper.toResponse(updatedTeam);
    }


    @Override
    public void deleteTeam(UUID teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new ResourceNotFoundException("Team not found");
        }
        teamRepository.deleteById(teamId);
    }

}
