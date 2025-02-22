package com.jahj.demo.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jahj.demo.model.OrderItem;
import com.jahj.demo.service.OrderItemService;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(OrderItemController.class)
public class OrderItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderItemService orderItemService;

    @Test
    public void testGetAllOrderItems() throws Exception {
        List<OrderItem> orderItems = Arrays.asList(new OrderItem(1L, 50));
        when(orderItemService.getAllOrderItems()).thenReturn(orderItems);

        mockMvc.perform(get("/api/order-items"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrderItemById() throws Exception {
        OrderItem orderItem = new OrderItem(1L, 50);
        when(orderItemService.getOrderItemById(1L)).thenReturn(orderItem);

        mockMvc.perform(get("/api/order-items/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateOrderItem() throws Exception {
        OrderItem createdOrderItem = new OrderItem(1L, 50);
        when(orderItemService.createOrderItem(Mockito.any(OrderItem.class))).thenReturn(createdOrderItem);

        mockMvc.perform(post("/api/order-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Item1\",\"price\":50.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrderItem() throws Exception {
        OrderItem updatedOrderItem = new OrderItem(1L, 60);
        when(orderItemService.updateOrderItem(1L, updatedOrderItem)).thenReturn(updatedOrderItem);

        mockMvc.perform(put("/api/order-items/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"quantity\":60.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOrderItem() throws Exception {
        doNothing().when(orderItemService).deleteOrderItem(1L);

        mockMvc.perform(delete("/api/order-items/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}

