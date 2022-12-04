package com.example.carsharing.service;

import com.example.carsharing.dto.OrderDto;
import com.example.carsharing.models.Auto;
import com.example.carsharing.models.Client;
import com.example.carsharing.models.Orders;
import com.example.carsharing.repository.AutoRepository;
import com.example.carsharing.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final AutoRepository autoRepository;
    private final OrderRepository orderRepository;

    public OrderService(AutoRepository autoRepository, OrderRepository orderRepository) {
        this.autoRepository = autoRepository;
        this.orderRepository = orderRepository;
    }

    public Orders save(OrderDto orderDto) {
        Orders order = new Orders(
                LocalDateTime.now().toString(),
                null,
                orderDto.getAuto(),
                orderDto.getConsultant(),
                orderDto.getClient(),
                orderDto.getTechSpecialist()
        );
        Auto auto = orderDto.getAuto();
        auto.setStatus("busy");
        autoRepository.save(auto);
        return orderRepository.save(order);
    }

    public List<Orders> getAllBusyAutoByClient(String phone) {
        return orderRepository
                .findAll()
                .stream()
                .filter(o -> o.getAuto().getStatus().equals("busy"))
                .filter(o -> o.getClient().getPhone().equals(phone))
                .filter(Orders::isDateReturnEmpty)
                .collect(Collectors.toList());
    }

    public List<Orders> getAllEndOrdersByClient(String phone) {
        return orderRepository
                .findAll()
                .stream()
                .filter(o -> o.getClient().getPhone().equals(phone))
                .filter(o -> !o.isDateReturnEmpty())
                .collect(Collectors.toList());
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Page<Orders> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.orderRepository.findAll(pageable);
    }

    public void endOrder(long id) {
        Orders order = orderRepository.findOrdersById(id);
        Auto auto = order.getAuto();
        auto.setStatus("free");
        autoRepository.save(auto);
        order.setDateReturn(LocalDateTime.now().toString());
        orderRepository.save(order);
    }
}
