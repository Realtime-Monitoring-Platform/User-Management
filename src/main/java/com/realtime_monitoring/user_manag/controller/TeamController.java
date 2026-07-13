package com.realtime_monitoring.user_manag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_monitoring.user_manag.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
}
