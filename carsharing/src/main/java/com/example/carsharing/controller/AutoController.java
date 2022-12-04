package com.example.carsharing.controller;

import com.example.carsharing.dto.AutoDto;
import com.example.carsharing.models.Auto;
import com.example.carsharing.models.TypeAuto;
import com.example.carsharing.service.AutoService;
import com.example.carsharing.service.TypeAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return findPaginated(1, model);
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
        AutoDto autoDto = new AutoDto(auto.getId(), auto.getColor(), auto.getMark(), auto.getTypeAuto().getId(), auto.getNumber());
        model.addAttribute("auto", autoDto);
        model.addAttribute("list", typeAutoService.getAll());
        return "update_auto";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(value = "auto") AutoDto autoDto) {
        autoService.update(autoDto);
        return "redirect:/auto/showFormForUpdate/" + autoDto.getId() + "?success";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;

        Page<Auto> page = autoService.findPaginated(pageNo, pageSize);
        List<Auto> list = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("list", list);
        return "auto";
    }

}
