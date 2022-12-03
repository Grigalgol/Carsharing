package com.example.carsharing.controller;

import com.example.carsharing.dto.TypeAutoDto;
import com.example.carsharing.models.TypeAuto;
import com.example.carsharing.service.TypeAutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/typeAuto")
public class TypeAutoController {

    private final TypeAutoService typeAutoService;

    public TypeAutoController(TypeAutoService typeAutoService) {
        this.typeAutoService = typeAutoService;
    }

    @GetMapping
    public String showTypeAutoPage(Model model) {
        model.addAttribute("list", typeAutoService.getAll());
        return "typeAuto";
    }

    @GetMapping("/showNewTypeAutoForm")
    public String showNewTypeAutoForm(Model model) {
        model.addAttribute("typeAuto", new TypeAutoDto());
        return "new_type_auto";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(value = "typeAuto") TypeAutoDto typeAutoDto) {
        typeAutoService.save(typeAutoDto);
        return "redirect:/typeAuto/showNewTypeAutoForm?success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        typeAutoService.deleteById(id);
        return "redirect:/typeAuto";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
        TypeAuto typeAuto = typeAutoService.getById(id);
        TypeAutoDto typeAutoDto = new TypeAutoDto(typeAuto.getId(), typeAuto.getFuelType(), typeAuto.getEngineType());
        model.addAttribute("typeAuto", typeAutoDto);
        return "update_type_auto";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(value = "specialist")TypeAutoDto typeAutoDto) {
        typeAutoService.update(typeAutoDto);
        return "redirect:/typeAuto/showFormForUpdate/" + typeAutoDto.getId() + "?success";
    }
}
