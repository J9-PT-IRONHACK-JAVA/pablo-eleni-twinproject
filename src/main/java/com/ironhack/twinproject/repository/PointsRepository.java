package com.ironhack.twinproject.repository;

import com.ironhack.twinproject.dto.Points;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, Long> {
}
