package com.floorballmanager.floorballteammanager.repository;

import org.springframework.data.repository.CrudRepository;
import com.floorballmanager.floorballteammanager.domain.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
