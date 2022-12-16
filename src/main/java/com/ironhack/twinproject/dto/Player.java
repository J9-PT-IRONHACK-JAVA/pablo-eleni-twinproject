package com.ironhack.twinproject.dto;

import com.ironhack.twinproject.repository.PointsRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String name;


    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "points_id")
    private Points points;

    private int totalPoints;

    public Player(String name) {
        this.name = name;
        this.points = new Points();
    }
    public void addPoints(int value, CategoryTypes category) {
            points.addPoints(value, category);
    }
}