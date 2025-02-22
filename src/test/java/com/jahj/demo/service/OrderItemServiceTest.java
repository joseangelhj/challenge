package com.jahj.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.jahj.demo.model.OrderItem;
import com.jahj.demo.repository.OrderItemRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderItemServiceTest {

    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrderItems() {
        List<OrderItem> orderItems = Arrays.asList(new OrderItem(1L, 50));
        when(orderItemRepository.findAll()).thenReturn(orderItems);

        List<OrderItem> result = orderItemService.getAllOrderItems();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetOrderItemById() {
        OrderItem orderItem = new OrderItem(1L, 50);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));

        OrderItem result = orderItemService.getOrderItemById(1L);
        assertNotNull(result);
    }

    @Test
    public void testCreateOrderItem() {
        OrderItem orderItem = new OrderItem(null, 50);
        OrderItem createdOrderItem = new OrderItem(1L, 50);
        when(orderItemRepository.save(orderItem)).thenReturn(createdOrderItem);

        OrderItem result = orderItemService.createOrderItem(orderItem);
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
    }

    @Test
    public void testUpdateOrderItem() {
        OrderItem existingOrderItem = new OrderItem(1L, 50);
        OrderItem updatedOrderItem = new OrderItem(1L, 60);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(existingOrderItem));
        when(orderItemRepository.save(updatedOrderItem)).thenReturn(updatedOrderItem);

        OrderItem result = orderItemService.updateOrderItem(1L, updatedOrderItem);
        assertNull(result);
    }

    @Test
    public void testDeleteOrderItem() {
        OrderItem orderItem = new OrderItem(1L, 50);
        when(orderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));

        orderItemService.deleteOrderItem(1L);

        verify(orderItemRepository, times(1)).delete(orderItem);
    }
}

