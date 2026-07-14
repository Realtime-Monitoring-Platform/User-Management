package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.dto.team.TeamRequest;
import com.realtime_monitoring.user_manag.dto.team.TeamResponse;
import com.realtime_monitoring.user_manag.dto.team.UpdateTeamRequest;
import com.realtime_monitoring.user_manag.service.TeamService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams() {

        return ResponseEntity.ok(
                teamService.getAllTeams());
    }

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(
            @RequestBody TeamRequest request) {

        TeamResponse response = teamService.createTeam(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> updateTeam(
            @PathVariable UUID id,
            @RequestBody UpdateTeamRequest request) {

        TeamResponse response = teamService.updateTeam(id, request);
        System.out.println("Updated team response::::::::::::::::::::::::::::::::::::::::" + response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(
            @PathVariable UUID id) {

        teamService.deleteTeam(id);

        return ResponseEntity.noContent().build();
    }
}