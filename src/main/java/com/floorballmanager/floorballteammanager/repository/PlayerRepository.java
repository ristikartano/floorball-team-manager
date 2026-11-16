package com.floorballmanager.floorballteammanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.floorballmanager.floorballteammanager.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> findByTeam_Id(Long teamId);

}
