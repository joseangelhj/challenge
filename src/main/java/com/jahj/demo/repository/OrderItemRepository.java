package com.jahj.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jahj.demo.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
