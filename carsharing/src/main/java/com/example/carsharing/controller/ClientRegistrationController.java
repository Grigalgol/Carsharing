package com.example.carsharing.controller;

import com.example.carsharing.dto.ClientDto;
import com.example.carsharing.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class ClientRegistrationController {
    private final ClientService clientService;

    public ClientRegistrationController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    public String registerUserAccount(@ModelAttribute("user") ClientDto clientDto) {
        clientService.save(clientDto);
        return "redirect:/registration?success";
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new ClientDto());
        return "registration";
    }
}
