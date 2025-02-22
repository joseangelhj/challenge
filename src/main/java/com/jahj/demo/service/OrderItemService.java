package com.jahj.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jahj.demo.exception.ResourceNotFoundException;
import com.jahj.demo.model.OrderItem;
import com.jahj.demo.repository.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem not found"));
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        OrderItem orderItem = getOrderItemById(id);
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setProduct(orderItemDetails.getProduct());
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long id) {
        OrderItem orderItem = getOrderItemById(id);
        orderItemRepository.delete(orderItem);
    }
}

