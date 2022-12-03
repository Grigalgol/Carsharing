package com.example.carsharing.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
@Data
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String firstName;
    private String lastName;
    private String passportData;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.REMOVE)
    List<Orders> orders;

    public Client(String phone, String firstName, String lastName, String passportData) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportData = passportData;
    }
}
