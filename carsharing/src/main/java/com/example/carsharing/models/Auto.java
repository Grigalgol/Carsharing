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

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeAuto typeAuto;

    public Auto(String color, String mark, TypeAuto typeAuto) {
        this.color = color;
        this.mark = mark;
        this.typeAuto = typeAuto;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "auto", cascade = CascadeType.ALL)
    List<Orders> orders;
}