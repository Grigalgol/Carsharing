package com.example.carsharing.service;

import com.example.carsharing.dto.EmployeeDto;
import com.example.carsharing.models.Consultant;
import com.example.carsharing.models.TechSpecialist;
import com.example.carsharing.repository.ConsultantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantService {
    private final ConsultantRepository consultantRepository;

    public ConsultantService(ConsultantRepository consultantRepository) {
        this.consultantRepository = consultantRepository;
    }

    public Consultant save(EmployeeDto employeeDto) {
        Consultant consultant = new Consultant(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getPasswordSeries() + employeeDto.getPasswordNumber(),
                employeeDto.getEmploymentContract()
        );
        return consultantRepository.save(consultant);
    }

    public List<Consultant> getAll() {
        return consultantRepository.findAll();
    }

    public void deleteById(long id) {
        consultantRepository.deleteById(id);
    }

    public Consultant findById(long id) {
        return consultantRepository.findById(id);
    }

    public Consultant update(EmployeeDto employeeDto) {
        Consultant consultant = new Consultant(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getPasswordSeries() + employeeDto.getPasswordNumber(),
                employeeDto.getEmploymentContract()
        );
        consultant.setId(employeeDto.getId());
        return consultantRepository.save(consultant);
    }
    public Page<Consultant> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.consultantRepository.findAll(pageable);
    }
}
