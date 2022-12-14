package com.example.carsharing.controller;

import com.example.carsharing.dto.EmployeeDto;
import com.example.carsharing.models.Consultant;
import com.example.carsharing.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultant")
public class ConsultantController {
    private final ConsultantService consultantService;

    @Autowired
    public ConsultantController(ConsultantService techSpecialistService) {
        this.consultantService = techSpecialistService;
    }

    @GetMapping
    public String showConsultantPage(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/showNewConsultantForm")
    public String showNewConsultantForm(Model model) {
        model.addAttribute("consultant", new EmployeeDto());
        return "new_consultant";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(value = "consultant") EmployeeDto employeeDto) {
        consultantService.save(employeeDto);
        return "redirect:/consultant/showNewConsultantForm?success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        consultantService.deleteById(id);
        return "redirect:/consultant";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Consultant consultant = consultantService.findById(id);
        EmployeeDto employeeDto = new EmployeeDto(
                consultant.getId(),
                consultant.getFirstName(),
                consultant.getLastName(),
                consultant.getPassportData().substring(0, 4),
                consultant.getPassportData().substring(4),
                consultant.getEmploymentContract()
        );
        model.addAttribute("consultant", employeeDto);
        return "update_consultant";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(value = "consultant") EmployeeDto employeeDto) {
        consultantService.update(employeeDto);
        return "redirect:/consultant/showFormForUpdate/" + employeeDto.getId() + "?success";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;

        Page<Consultant> page = consultantService.findPaginated(pageNo, pageSize);
        List<Consultant> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("list", listEmployees);
        return "consultant";
    }
}
