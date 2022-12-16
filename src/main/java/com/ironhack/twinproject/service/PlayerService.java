package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.CategoryTypes;
import com.ironhack.twinproject.dto.Player;
import com.ironhack.twinproject.dto.Points;
import com.ironhack.twinproject.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    private Points points;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(long selectedId) {
        return playerRepository.findById(selectedId);
    }

    public Optional<Player> findByNameIgnoreCase(String name) {
        return playerRepository.findByNameIgnoreCase(name);
    }

    public Player save(Player user) {
        return playerRepository.save(user);
    }

    public List<Player> saveAll(List<Player> listOfPlayers) {
        return playerRepository.saveAll(listOfPlayers);
    }

}