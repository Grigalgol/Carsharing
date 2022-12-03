package com.example.carsharing.repository;

import com.example.carsharing.models.TypeAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAutoRepository extends JpaRepository<TypeAuto, Long> {
    TypeAuto findById(long id);
}
