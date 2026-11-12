package com.floorballmanager.floorballteammanager.repository;

import org.springframework.data.repository.CrudRepository;
import com.floorballmanager.floorballteammanager.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

}
