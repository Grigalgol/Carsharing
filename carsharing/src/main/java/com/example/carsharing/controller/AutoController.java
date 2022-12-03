package com.example.carsharing.controller;

import com.example.carsharing.dto.AutoDto;
import com.example.carsharing.models.Auto;
import com.example.carsharing.service.AutoService;
import com.example.carsharing.service.TypeAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auto")
public class AutoController {

    private final AutoService autoService;
    private final TypeAutoService typeAutoService;

    @Autowired
    public AutoController(AutoService autoService, TypeAutoService typeAutoService) {
        this.autoService = autoService;
        this.typeAutoService = typeAutoService;
    }

    @GetMapping
    public String showAutoPage(Model model) {
        model.addAttribute("list", autoService.getAll());
        return "auto";
    }

    @GetMapping("/showNewAutoForm")
    public String showAutoForm(Model model) {
        model.addAttribute("auto", new AutoDto());
        model.addAttribute("list", typeAutoService.getAll());
        return "new_auto";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute(value = "auto") AutoDto autoDto) {
        autoService.save(autoDto);
        return "redirect:/auto/showNewAutoForm?success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        autoService.deleteById(id);
        return "redirect:/auto";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {
        Auto auto = autoService.findById(id);
        AutoDto autoDto = new AutoDto(auto.getId(), auto.getColor(), auto.getMark(), auto.getTypeAuto().getId());
        model.addAttribute("auto", autoDto);
        model.addAttribute("list", typeAutoService.getAll());
        return "update_auto";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(value = "auto") AutoDto autoDto) {
        autoService.update(autoDto);
        return "redirect:/auto/showFormForUpdate/" + autoDto.getId() + "?success";
    }
}
