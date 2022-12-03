package com.example.carsharing.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateTaking;
    private String dateReturn;

    @ManyToOne(fetch = FetchType.EAGER)
    private Auto auto;

    @ManyToOne(fetch = FetchType.EAGER)
    private Consultant consultant;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;


    @ManyToOne(fetch = FetchType.EAGER)
    private TechSpecialist techSpecialist;

    public Orders(String dateTaking, String dateReturn, Auto auto, Consultant consultant, Client client, TechSpecialist techSpecialist) {
        this.dateTaking = dateTaking;
        this.dateReturn = dateReturn;
        this.auto = auto;
        this.consultant = consultant;
        this.client = client;
        this.techSpecialist = techSpecialist;
    }

    public boolean isDateReturnEmpty() {
        return dateReturn == null;
    }
}
