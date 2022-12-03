package com.example.carsharing.dto;

import com.example.carsharing.models.TypeAuto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoDto {
    private Long id;
    private String color;
    private String mark;
    private Long typeAuto;
    private String number;
}
