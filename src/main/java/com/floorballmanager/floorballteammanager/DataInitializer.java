package com.floorballmanager.floorballteammanager;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.floorballmanager.floorballteammanager.repository.TeamRepository;
import com.floorballmanager.floorballteammanager.domain.Player;
import com.floorballmanager.floorballteammanager.domain.Team;
import com.floorballmanager.floorballteammanager.repository.PlayerRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(TeamRepository teamRepository, PlayerRepository playerRepository) {
        return args -> {
            Team team = new Team("Indians U13");
            teamRepository.save(team);

            Player player = new Player("Timo", "Virtanen");
            player.setTeam(team);
            playerRepository.save(player);
        };
    }

}
