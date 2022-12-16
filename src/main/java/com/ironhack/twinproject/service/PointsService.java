package com.ironhack.twinproject.service;

import com.ironhack.twinproject.dto.Points;
import com.ironhack.twinproject.repository.PointsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointsService {

    private final PointsRepository pointsRepository;

    public Points createOrUpdate(Points points) {
        return pointsRepository.save(points);
    }

}
