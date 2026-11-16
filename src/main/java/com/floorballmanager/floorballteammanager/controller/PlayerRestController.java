package com.floorballmanager.floorballteammanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.floorballmanager.floorballteammanager.domain.Player;
import com.floorballmanager.floorballteammanager.repository.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;


 // Palauttaa pelaajien tiedot JSON-muodossa HTML-sivun sijaan
@RestController
@RequestMapping("/api")
public class PlayerRestController {

    private final PlayerRepository playerRepository;

    public PlayerRestController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public Iterable<Player> getPlayers() {
        Iterable<Player> players = playerRepository.findAll();
        return players;
    }
    

}
