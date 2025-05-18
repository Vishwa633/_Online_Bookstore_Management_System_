package com.example.bookstore.ordermanagement;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
    private final Map<String, List<OrderDTO>> userOrders = new HashMap<>();

    @PostMapping
    public synchronized void createOrder(@RequestBody OrderDTO order) {
        userOrders.computeIfAbsent(order.getUserId(), k -> new ArrayList<>()).add(order);
    }

    @GetMapping
    public List<OrderDTO> getOrders(@RequestParam String userId) {
        return userOrders.getOrDefault(userId, Collections.emptyList());
    }
}