package com.floorballmanager.floorballteammanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.floorballmanager.floorballteammanager.repository.TeamRepository;
import com.floorballmanager.floorballteammanager.repository.PlayerRepository;
import com.floorballmanager.floorballteammanager.domain.Player;
import com.floorballmanager.floorballteammanager.domain.Team;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FloorballteammanagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	@Transactional
	void testTeamSaving() {

		Team team = new Team();
		team.setName("Testijoukkue");
		Team savedTeam = teamRepository.save(team);
		Team foundTeam = teamRepository.findById(savedTeam.getId()).orElseThrow();
		assertEquals("Testijoukkue", foundTeam.getName());

	}

	@Test
	@Transactional
	void testPlayerSaving() {

		Player player = new Player();
		player.setFirstName("Matti");
		player.setLastName("Meikäläinen");
		player.setJerseyNumber(25);
		Player savedPlayer = playerRepository.save(player);
		Player foundPlayer = playerRepository.findById(savedPlayer.getId()).orElseThrow();
		assertEquals("Matti", foundPlayer.getFirstName());
		assertEquals("Meikäläinen", foundPlayer.getLastName());
		assertEquals(25, foundPlayer.getJerseyNumber());

	}

	@Test
	@Transactional
	void testPlayerTeamRelationship() {

		Team team = new Team();
		team.setName("Testijoukkue");

		Team savedTeam = teamRepository.save(team);

		Player player = new Player();
		player.setFirstName("Matti");
		player.setLastName("Meikäläinen");
		player.setJerseyNumber(25);

		player.setTeam(savedTeam);

		Player savedPlayer = playerRepository.save(player);

		Player foundPlayer = playerRepository.findById(savedPlayer.getId()).orElseThrow();

		assertEquals(savedTeam.getId(), foundPlayer.getTeam().getId());
	}

}
