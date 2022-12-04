package com.example.carsharing.controller;

import com.example.carsharing.models.Auto;
import com.example.carsharing.models.Client;
import com.example.carsharing.models.Orders;
import com.example.carsharing.service.ClientService;
import com.example.carsharing.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        return findPaginatedClients(1, model);
    }

    @GetMapping("/allOrders")
    public String getAllOrders(Model model) {
        return findPaginatedOrders(1, model);
    }

    @GetMapping("/pageC/{pageNo}")
    public String findPaginatedClients(@PathVariable(value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;

        Page<Client> page = clientService.findPaginated(pageNo, pageSize);
        List<Client> list = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("list", list);
        return "clients";
    }

    @GetMapping("/pageO/{pageNo}")
    public String findPaginatedOrders(@PathVariable(value = "pageNo") int pageNo,
                                       Model model) {
        int pageSize = 5;

        Page<Orders> page = orderService.findPaginated(pageNo, pageSize);
        List<Orders> list = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("list", list);
        return "orders";
    }

}
