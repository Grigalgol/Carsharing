package com.example.carsharing.service;

import com.example.carsharing.dto.AutoDto;
import com.example.carsharing.models.Auto;
import com.example.carsharing.repository.AutoRepository;
import com.example.carsharing.repository.TypeAutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    private final AutoRepository autoRepository;
    private final TypeAutoRepository typeAutoRepository;

    @Autowired
    public AutoService(AutoRepository autoRepository, TypeAutoRepository typeAutoRepository) {
        this.autoRepository = autoRepository;
        this.typeAutoRepository = typeAutoRepository;
    }

    public Auto save(AutoDto autoDto) {
        Auto auto = new Auto(autoDto.getColor(), autoDto.getMark(), typeAutoRepository.findById(autoDto.getTypeAuto()).get());
        return autoRepository.save(auto);
    }

    public List<Auto> getAll() {
        return autoRepository.findAll();
    }

    public Auto findById(long id) {
        return autoRepository.findById(id);
    }

    public void deleteById(long id) {
        autoRepository.deleteById(id);
    }

    public Auto update(AutoDto autoDto) {
        Auto auto = new Auto(autoDto.getColor(), autoDto.getMark(), typeAutoRepository.findById(autoDto.getTypeAuto()).get());
        auto.setId(autoDto.getId());
        return autoRepository.save(auto);
    }
}
