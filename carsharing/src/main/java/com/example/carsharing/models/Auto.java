package com.example.carsharing.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "auto")
@Data
@NoArgsConstructor
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private String mark;
    private String status;
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeAuto typeAuto;

    public Auto(String color, String mark, TypeAuto typeAuto, String number) {
        this.color = color;
        this.mark = mark;
        this.typeAuto = typeAuto;
        status = "free";
        this.number = number;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "auto", cascade = CascadeType.REMOVE)
    List<Orders> orders;
}
