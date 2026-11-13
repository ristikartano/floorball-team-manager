package com.floorballmanager.floorballteammanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.floorballmanager.floorballteammanager.repository.TeamRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.floorballmanager.floorballteammanager.domain.Team;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


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

    @GetMapping("/teams/new")
    public String showTeamForm(Model model) {
        model.addAttribute("team", new Team());
        return "teamform";
    }

    @PostMapping("/teams")
    public String saveTeam(@ModelAttribute Team team) {
        teamRepository.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/teams/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Team team = teamRepository.findById(id).get();
        model.addAttribute("team", team);
        return "teamform";
    }

    @PostMapping("/teams/edit/{id}")
    public String updateTeam(@PathVariable Long id, @ModelAttribute Team formTeam) {
        Team team = teamRepository.findById(id).get();
        team.setName(formTeam.getName());
        teamRepository.save(team);
        return "redirect:/teams";
    }

    @PostMapping("/teams/delete/{id}")
    public String deleteTeam(@PathVariable Long id) {
        teamRepository.deleteById(id);
        return "redirect:/teams";
    }
    

}
