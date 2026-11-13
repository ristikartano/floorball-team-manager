package com.floorballmanager.floorballteammanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.floorballmanager.floorballteammanager.domain.Player;
import com.floorballmanager.floorballteammanager.domain.Team;
import com.floorballmanager.floorballteammanager.repository.PlayerRepository;
import com.floorballmanager.floorballteammanager.repository.TeamRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerController(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/players")
    public String playerList(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "playerlist";
    }

    @GetMapping("/players/new")
    public String showPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamRepository.findAll());
        return "playerform";
    }

    @PostMapping("/players")
    public String savePlayer(@ModelAttribute Player player, @RequestParam Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        player.setTeam(team);
        playerRepository.save(player);
        return "redirect:/players";
    }

    @GetMapping("/players/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Player player = playerRepository.findById(id).get();
        model.addAttribute("player", player);
        model.addAttribute("teams", teamRepository.findAll());
        return "playerform";
    }

    @PostMapping("/players/edit/{id}")
    public String updatePlayer(@PathVariable Long id, @ModelAttribute Player formPlayer, @RequestParam Long teamId) {

        Player player = playerRepository.findById(id).get();

        player.setFirstName(formPlayer.getFirstName());
        player.setLastName(formPlayer.getLastName());

        Team team = teamRepository.findById(teamId).get();
        player.setTeam(team);

        playerRepository.save(player);

        return "redirect:/players";
    }

}
