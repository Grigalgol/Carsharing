package com.example.carsharing.service;

import com.example.carsharing.dto.EmployeeDto;
import com.example.carsharing.models.TechSpecialist;
import com.example.carsharing.repository.TechSpecialistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechSpecialistService {

    private final TechSpecialistRepository techSpecialistRepository;

    public TechSpecialistService(TechSpecialistRepository techSpecialistRepository) {
        this.techSpecialistRepository = techSpecialistRepository;
    }

    public TechSpecialist save(EmployeeDto employeeDto) {
        TechSpecialist techSpecialist = new TechSpecialist(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getPasswordSeries() + employeeDto.getPasswordNumber(),
                employeeDto.getEmploymentContract()
        );
        return techSpecialistRepository.save(techSpecialist);
    }

    public List<TechSpecialist> getAll() {
        return techSpecialistRepository.findAll();
    }

    public void deleteById(long id) {
        techSpecialistRepository.deleteById(id);
    }

    public TechSpecialist  findById(long id) {
        return techSpecialistRepository.findById(id);
    }

    public TechSpecialist update(EmployeeDto employeeDto) {
        TechSpecialist techSpecialist = new TechSpecialist(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getPasswordSeries() + employeeDto.getPasswordNumber(),
                employeeDto.getEmploymentContract()
        );
        techSpecialist.setId(employeeDto.getId());
        return techSpecialistRepository.save(techSpecialist);
    }
    public Page<TechSpecialist> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.techSpecialistRepository.findAll(pageable);
    }
}
