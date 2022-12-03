package com.example.carsharing.dto;

import com.example.carsharing.models.Auto;
import com.example.carsharing.models.Client;
import com.example.carsharing.models.Consultant;
import com.example.carsharing.models.TechSpecialist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private Auto auto;
    private Consultant consultant;
    private Client client;
    private TechSpecialist techSpecialist;

    public OrderDto(Auto auto, Consultant consultant, Client client, TechSpecialist techSpecialist) {
        this.auto = auto;
        this.consultant = consultant;
        this.client = client;
        this.techSpecialist = techSpecialist;
    }
}
