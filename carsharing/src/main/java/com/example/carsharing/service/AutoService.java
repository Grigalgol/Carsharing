package com.example.carsharing.service;

import com.example.carsharing.models.Auto;
import com.example.carsharing.repository.AutoRepository;
import org.springframework.stereotype.Service;

@Service
public class AutoService {

    private final AutoRepository autoRepository;

    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public Auto save() {
        return null;
    }
}
