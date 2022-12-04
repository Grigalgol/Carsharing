package com.example.carsharing.controller;

import com.example.carsharing.dto.EmployeeDto;
import com.example.carsharing.models.TechSpecialist;
import com.example.carsharing.service.TechSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/techSpecialist")
public class TechSpecialistController {

    private final TechSpecialistService techSpecialistService;

    @Autowired
    public TechSpecialistController(TechSpecialistService techSpecialistService) {
        this.techSpecialistService = techSpecialistService;
    }

    @GetMapping
    public String showTechSpecialistPage(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/showNewTechSpecialistForm")
    public String showNewTechSpecialistForm(Model model) {
        model.addAttribute("specilist", new EmployeeDto());
        return "new_tech_specialist";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(value = "specialist") EmployeeDto employeeDto) {
        techSpecialistService.save(employeeDto);
        return "redirect:/techSpecialist/showNewTechSpecialistForm?success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        techSpecialistService.deleteById(id);
        return "redirect:/techSpecialist";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
        TechSpecialist techSpecialist = techSpecialistService.findById(id);
        EmployeeDto employeeDto = new EmployeeDto(
                techSpecialist.getId(),
                techSpecialist.getFirstName(),
                techSpecialist.getLastName(),
                techSpecialist.getPassportData().substring(0, 4),
                techSpecialist.getPassportData().substring(4),
                techSpecialist.getEmploymentContract()
        );
        model.addAttribute("specilist", employeeDto);
        return "update_tech_specialist";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(value = "specialist") EmployeeDto employeeDto) {
        techSpecialistService.update(employeeDto);
        return "redirect:/techSpecialist/showFormForUpdate/" + employeeDto.getId() + "?success";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;

        Page<TechSpecialist> page = techSpecialistService.findPaginated(pageNo, pageSize);
        List<TechSpecialist> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("list", listEmployees);
        return "techSpecialist";
    }
}
