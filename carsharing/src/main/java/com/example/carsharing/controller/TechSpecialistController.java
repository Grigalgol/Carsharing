package com.example.carsharing.controller;

import com.example.carsharing.dto.EmployeeDto;
import com.example.carsharing.models.TechSpecialist;
import com.example.carsharing.service.TechSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("list", techSpecialistService.getAll());
        return "techSpecialist";
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
    public String showNewTechSpecialistForm(@PathVariable(value = "id") long id, Model model) {
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
}
