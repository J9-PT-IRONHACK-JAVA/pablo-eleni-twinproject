package com.ironhack.twinproject.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int theOlympicsPoints;
    private int usCitiesPoints;
    private int historyPoints;
    private int musicPoints;
    private int carsPoints;
    private int totalPoints;

    @OneToOne(mappedBy = "player")
    private Player player;

    public Points(int theOlympicsPoints,
                  int usCitiesPoints,
                  int historyPoints,
                  int musicPoints,
                  int carsPoints) {
        this.theOlympicsPoints = theOlympicsPoints;
        this.usCitiesPoints = usCitiesPoints;
        this.historyPoints = historyPoints;
        this.musicPoints = musicPoints;
        this.carsPoints = carsPoints;
        setTotalPoints();

    }

    public void setTotalPoints( ){
        this.totalPoints = theOlympicsPoints + usCitiesPoints + historyPoints + musicPoints + carsPoints;
    }
}
