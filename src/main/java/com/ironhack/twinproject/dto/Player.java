package com.ironhack.twinproject.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @OneToOne
    @JoinColumn (name = "points_id")
    private Points points;

    private int totalPoints;

    public Player(String name) {
        this.name = name;
    }
}