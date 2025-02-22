package com.jahj.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.jahj.demo.model.Order;
import com.jahj.demo.repository.OrderRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = Arrays.asList(new Order(1L, LocalDate.now(), "new"));
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("new", result.get(0).getStatus());
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order(1L, LocalDate.now(), "new");
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(1L);
        assertNotNull(result);
        assertEquals("new", result.getStatus());
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order(null, LocalDate.now(), "new");
        Order createdOrder = new Order(1L, LocalDate.now(), "new");
        when(orderRepository.save(order)).thenReturn(createdOrder);

        Order result = orderService.createOrder(order);
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
        assertEquals("new", result.getStatus());
    }

    @Test
    public void testDeleteOrder() {
        Order order = new Order(1L, LocalDate.now(), "new");
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).delete(order);
    }
}

