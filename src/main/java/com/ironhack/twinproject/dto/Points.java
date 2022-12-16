package com.ironhack.twinproject.dto;

import com.ironhack.twinproject.repository.PointsRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

    @OneToOne(mappedBy = "points")
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

    public void addPoints(int value, CategoryTypes category) {
        this.totalPoints = this.totalPoints + value;

        switch (category) {
            case CARS:
                this.carsPoints = this.carsPoints + value;
                break;

            case MUSIC:
                this.musicPoints = this.musicPoints + value;
                break;

            case CITIES:
                this.usCitiesPoints = this.usCitiesPoints + value;
                break;

            case OLYMPICS:
                this.theOlympicsPoints = this.theOlympicsPoints + value;

                break;

            case HISTORY:
                this.historyPoints = this.historyPoints + value;
                break;
        }
    }
}
