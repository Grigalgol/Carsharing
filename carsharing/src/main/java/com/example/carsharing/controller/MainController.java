package com.example.carsharing.controller;

import com.example.carsharing.dto.AutoDto;
import com.example.carsharing.dto.OrderDto;
import com.example.carsharing.dto.UserDto;
import com.example.carsharing.models.Auto;
import com.example.carsharing.models.Client;
import com.example.carsharing.models.Consultant;
import com.example.carsharing.models.TechSpecialist;
import com.example.carsharing.service.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    private final AutoService autoService;
    private final ClientService clientService;
    private final TechSpecialistService techSpecialistService;
    private final ConsultantService consultantService;
    private final OrderService orderService;
    private Authentication authentication;

    public MainController(AutoService autoService, ClientService clientService, TechSpecialistService techSpecialistService, ConsultantService consultantService, OrderService orderService) {
        this.autoService = autoService;
        this.clientService = clientService;
        this.techSpecialistService = techSpecialistService;
        this.consultantService = consultantService;
        this.orderService = orderService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return  "login";
    }

    @GetMapping("/getAuto")
    public String getAuto(Model model) {
        model.addAttribute("list", autoService.getAllFreeAuto());
        return "get_auto";
    }

    @PostMapping("/find")
    public String getFindAutoPage(@ModelAttribute(value = "auto") AutoDto autoDto, Model model) {
        model.addAttribute("list", autoService.getAllFreeFindAuto(autoDto));
        return "get_auto";
    }

    @GetMapping("/newOrder/{id}")
    public String newOrder(@PathVariable(value = "id") long id) {
        Auto auto = autoService.findById(id);
        Random random = new Random();
        List<TechSpecialist> listT = techSpecialistService.getAll();
        TechSpecialist techSpecialist = listT.get(random.nextInt(listT.size()));
        List<Consultant> listC = consultantService.getAll();
        Consultant consultant = listC.get(random.nextInt(listC.size()));
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        Client client = clientService.findByPhone(phone);
        OrderDto orderDto = new OrderDto(auto, consultant, client, techSpecialist);
        orderService.save(orderDto);
        return "redirect:/getAuto";
    }

    @GetMapping("/currentOrder")
    public String currentOrder(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        model.addAttribute("list", orderService.getAllBusyAutoByClient(phone));
        return "currentAuto";
    }

    @GetMapping("/endOrder/{id}")
    public String endOrder(@PathVariable(value = "id") long id) {
        orderService.endOrder(id);
        return "redirect:/currentOrder";
    }

    @GetMapping("/endedOrders")
    public String endOrder(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        model.addAttribute("list", orderService.getAllEndOrdersByClient(phone));
        return "endedOrders";
    }

    @GetMapping("/updateForm")
    public String showFormForClientUpdate(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        Client client = clientService.findByPhone(phone);
        UserDto userDto = new UserDto(
                client.getFirstName(),
                client.getLastName(),
                client.getPassportData().substring(0, 4),
                client.getPassportData().substring(4)
        );
        userDto.setPhone(phone);
        model.addAttribute("client", userDto);
        return "update_client";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute(value = "client") UserDto userDto) {
        clientService.updateClient(userDto);
        return "redirect:/updateForm?success";
    }

    @GetMapping("/findAutoForm")
    public String findAutoForm(Model model) {
        model.addAttribute("auto", new AutoDto());
        return "find_auto";
    }
}
