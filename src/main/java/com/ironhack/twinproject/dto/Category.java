package com.ironhack.twinproject.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
public class Category {
    @Id
    private Long id;
    private String title;
}
