package com.example.carsharing.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_auto")
@Data
@NoArgsConstructor
public class TypeAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fuelType;
    private String engineType;

    public TypeAuto(String fuelType, String engineType) {
        this.fuelType = fuelType;
        this.engineType = engineType;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typeAuto", cascade = CascadeType.ALL)
    List<Auto> auto;
}
