package com.example.carsharing.repository;

import com.example.carsharing.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findOrdersById(long id);
}
