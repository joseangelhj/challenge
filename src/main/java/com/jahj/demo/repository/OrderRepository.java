package com.jahj.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jahj.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
