package com.ironhack.twinproject.demo;

import com.ironhack.twinproject.dto.Player;
import com.ironhack.twinproject.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.annotations.Loader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("demo-data")
@RequiredArgsConstructor
@Log
public class GameDataLoader {

    private final PlayerRepository playerRepository;

    @EventListener (ApplicationReadyEvent.class)
    public void loadGameDemoData () {
        log.info("Loading demo players");
        var player1 = new Player("Ivan");
        var player2 = new Player("Steve");
        playerRepository.save(player1);
        playerRepository.save(player2);
    }

}