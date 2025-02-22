package com.jahj.demo.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jahj.demo.model.Order;
import com.jahj.demo.service.OrderService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;
    
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        List<Order> orders = Arrays.asList(new Order(1L, LocalDate.now(), "new"));
        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order(1L, LocalDate.now(), "new");
        when(orderService.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(get("/api/orders/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order createdOrder = new Order(1L, LocalDate.now(), "new");
        when(orderService.createOrder(Mockito.any(Order.class))).thenReturn(createdOrder);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Order1\",\"total\":100.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order updatedOrder = new Order(1L, LocalDate.now(), "new");
        when(orderService.updateOrder(1L, updatedOrder)).thenReturn(updatedOrder);

        mockMvc.perform(put("/api/orders/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"UpdatedOrder\",\"total\":150.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/api/orders/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
