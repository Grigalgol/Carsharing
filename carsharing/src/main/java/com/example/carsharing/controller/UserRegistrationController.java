package com.example.carsharing.controller;

import com.example.carsharing.dto.UserDto;
import com.example.carsharing.service.ClientService;
import com.example.carsharing.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private final UserService userService;
    private final ClientService clientService;

    public UserRegistrationController(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @PostMapping("/save")
    public String registerUserAccount(@ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);
        clientService.save(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }
}
