package com.example.carsharing.service;

import com.example.carsharing.dto.TypeAutoDto;
import com.example.carsharing.models.TechSpecialist;
import com.example.carsharing.models.TypeAuto;
import com.example.carsharing.repository.TypeAutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAutoService {
    private final TypeAutoRepository typeAutoRepository;

    @Autowired
    public TypeAutoService(TypeAutoRepository typeAutoRepository) {
        this.typeAutoRepository = typeAutoRepository;
    }

    public TypeAuto save(TypeAutoDto typeAutoDto) {
        TypeAuto typeAuto = new TypeAuto(typeAutoDto.getFuelType(), typeAutoDto.getEngineType());
        return typeAutoRepository.save(typeAuto);
    }

    public List<TypeAuto> getAll() {
        return typeAutoRepository.findAll();
    }

    public TypeAuto getById(long id) {
        return typeAutoRepository.findById(id);
    }

    public void deleteById(long id) {
        typeAutoRepository.deleteById(id);
    }

    public TypeAuto update(TypeAutoDto typeAutoDto) {
        TypeAuto typeAuto = new TypeAuto(typeAutoDto.getFuelType(), typeAutoDto.getEngineType());
        typeAuto.setId(typeAutoDto.getId());
        return typeAutoRepository.save(typeAuto);
    }

    public Page<TypeAuto> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.typeAutoRepository.findAll(pageable);
    }
}
