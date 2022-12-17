package com.ironhack.twinproject.service;
import com.ironhack.twinproject.dto.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {
    private final PlayerService playerService;

    @Bean
    void loadData() {
       // var listOfPlayers = List.of(
       ////         new Player("Brian"),
       ////         new Player("Hasan"),
       ////         new Player("Olalla")
       //// );
        //var savedPlayersList = playerService.saveAll(listOfPlayers);
    }
}
