package com.example.carsharing.controller;

import com.example.carsharing.service.ClientService;
import com.example.carsharing.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClientService clientService;
    private final OrderService orderService;

    public AdminController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping("/allClients")
    public String getAllClient(Model model) {
        model.addAttribute("list", clientService.getAll());
        return "clients";
    }

    @GetMapping("/allOrders")
    public String getAllOrders(Model model) {
        model.addAttribute("list", orderService.getAllOrders());
        return "orders";
    }


}
