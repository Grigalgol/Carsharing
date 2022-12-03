package com.example.carsharing.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "consultants")
@Data
@NoArgsConstructor
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String passportData;
    private String employmentContract;

    public Consultant(String firstName, String lastName, String passportData, String employmentContract) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportData = passportData;
        this.employmentContract = employmentContract;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "consultant", cascade = CascadeType.REMOVE)
    List<Orders> orders;
}
