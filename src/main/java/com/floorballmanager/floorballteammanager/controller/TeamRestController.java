package com.floorballmanager.floorballteammanager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.floorballmanager.floorballteammanager.domain.Team;
import com.floorballmanager.floorballteammanager.repository.TeamRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class TeamRestController {

    private final TeamRepository teamRepository;

    public TeamRestController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public Iterable<Team> getTeams() {
        Iterable<Team> teams = teamRepository.findAll();
        return teams;
    }
    

}