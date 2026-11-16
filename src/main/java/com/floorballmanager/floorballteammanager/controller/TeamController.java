package com.floorballmanager.floorballteammanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.floorballmanager.floorballteammanager.repository.TeamRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.floorballmanager.floorballteammanager.domain.Player;
import com.floorballmanager.floorballteammanager.domain.Team;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.floorballmanager.floorballteammanager.repository.PlayerRepository;


@Controller
public class TeamController {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamController(TeamRepository teamRepository, PlayerRepository playerRepository) {
    this.teamRepository = teamRepository;
    this.playerRepository = playerRepository;
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
    public String saveTeam(@Valid @ModelAttribute Team team, BindingResult result) {
        if (result.hasErrors()) {
            return "teamform";
        } else {
            teamRepository.save(team);
            return "redirect:/teams";
        }
    }

    @GetMapping("/teams/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Team team = teamRepository.findById(id).get();
        model.addAttribute("team", team);
        model.addAttribute("editId", id);
        return "teamform";
    }

    @PostMapping("/teams/edit/{id}")
    public String updateTeam(@PathVariable Long id,
            @Valid @ModelAttribute Team formTeam,
            BindingResult result,
            Model model) {

        
        // Jos joukkueen nimi on virheellinen, palataan lomakkeelle
        // ja säilytetään muokkaustila
        if (result.hasErrors()) {
            model.addAttribute("editId", id);
            return "teamform";
        }

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

    @GetMapping("/teams/view/{id}")
    public String getViewPage(@PathVariable Long id, Model model) {
        Team team = teamRepository.findById(id).orElseThrow();
        model.addAttribute("team", team);


        // Haetaan valittuun joukkueeseen kuuluvat pelaajat
        // ja välitetään heidät joukkueen pelaajalistaan
        List<Player> players = playerRepository.findByTeam_Id(id);
        model.addAttribute("players", players);
        return "teamview";
    }
    

}
