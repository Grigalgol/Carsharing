package com.example.carsharing.service;

import com.example.carsharing.dto.AutoDto;
import com.example.carsharing.models.Auto;
import com.example.carsharing.models.Consultant;
import com.example.carsharing.repository.AutoRepository;
import com.example.carsharing.repository.TypeAutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Auto auto = new Auto(autoDto.getColor(), autoDto.getMark(), typeAutoRepository.findById(autoDto.getTypeAuto()).get(), autoDto.getNumber());
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
        Auto auto = new Auto(autoDto.getColor(), autoDto.getMark(), typeAutoRepository.findById(autoDto.getTypeAuto()).get(), autoDto.getNumber());
        auto.setId(autoDto.getId());
        return autoRepository.save(auto);
    }
    public List<Auto> getAllFreeAuto() {
        return autoRepository.findAll().stream().filter(a -> a.getStatus().equals("free")).collect(Collectors.toList());
    }

    public List<Auto> getAllFreeFindAuto(AutoDto autoDto) {
        return autoRepository
                .findAll()
                .stream()
                .filter(a -> a.getStatus().equals("free"))
                .filter(a -> a.getMark().equals(autoDto.getMark()))
                .filter(a -> a.getColor().equals(autoDto.getColor()))
                .collect(Collectors.toList());
    }

    public Page<Auto> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.autoRepository.findAll(pageable);
    }

}
