package com.example.carsharing.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String firstName;
    private String lastName;
    private String password;
    private String passportData;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> roles;

    public User(String phone, String firstName, String lastName, String password, String passportData, Collection<Role> roles) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passportData = passportData;
        this.roles = roles;
    }


}
