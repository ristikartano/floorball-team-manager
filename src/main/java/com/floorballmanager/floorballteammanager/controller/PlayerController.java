package com.floorballmanager.floorballteammanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.floorballmanager.floorballteammanager.repository.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public String playerList(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "playerlist";
    }

}
