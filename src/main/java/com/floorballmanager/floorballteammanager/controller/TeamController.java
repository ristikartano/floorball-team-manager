package com.floorballmanager.floorballteammanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.floorballmanager.floorballteammanager.repository.TeamRepository;
import org.springframework.ui.Model;

@Controller
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public String teamList(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "teamlist";
    }

}
