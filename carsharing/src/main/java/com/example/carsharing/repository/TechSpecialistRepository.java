package com.example.carsharing.repository;

import com.example.carsharing.models.TechSpecialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechSpecialistRepository extends JpaRepository<TechSpecialist, Long> {
    TechSpecialist findById(long id);
}
