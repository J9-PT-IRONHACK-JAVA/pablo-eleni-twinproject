package com.ironhack.twinproject.repository;

import com.ironhack.twinproject.dto.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByNameIgnoreCase(String selectedId);

    @Query(value = "SELECT p.name, t.total_points FROM player p INNER JOIN points t on p.points_id = t.id ORDER BY t.total_points desc", nativeQuery = true)
    List<Object[]> listPlayersByScore();
    @Query(value = "SELECT p.name, t.history_points FROM player p INNER JOIN points t on p.points_id = t.id ORDER BY t.history_points desc", nativeQuery = true)
    List<Object[]> listPlayersByScoreInHistory();

    @Query(value = "SELECT p.name, t.cars_points FROM player p INNER JOIN points t on p.points_id = t.id ORDER BY t.cars_points desc", nativeQuery = true)
    List<Object[]> listPlayersByScoreInMusic();

    @Query(value = "SELECT p.name, t.the_olympics_points FROM player p INNER JOIN points t on p.points_id = t.id ORDER BY t.the_olympics_points desc", nativeQuery = true)
    List<Object[]> listPlayersByScoreInOlympics();

    @Query(value = "SELECT p.name, t.us_cities_points FROM player p INNER JOIN points t on p.points_id = t.id ORDER BY t.us_cities_points desc", nativeQuery = true)
    List<Object[]> listPlayersByScoreInUSCities();

    @Query(value = "SELECT p.name, t.cars_points FROM player p INNER JOIN points t on p.points_id = t.id ORDER BY t.cars_points desc", nativeQuery = true)
    List<Object[]> listPlayersByScoreInCars();

}