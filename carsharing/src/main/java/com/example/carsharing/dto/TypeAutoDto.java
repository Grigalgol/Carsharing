package com.example.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAutoDto {
    private Long id;
    private String fuelType;
    private String engineType;
}
