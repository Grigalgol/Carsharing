package com.example.carsharing.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tech_specialists")
@Data
@NoArgsConstructor
public class TechSpecialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String passportData;
    private String employmentContract;

    public TechSpecialist(String firstName, String lastName, String passportData, String employmentContract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportData = passportData;
        this.employmentContract = employmentContract;
    }
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "techSpecialist", cascade = CascadeType.REMOVE)
    List<Orders> orders;
}
