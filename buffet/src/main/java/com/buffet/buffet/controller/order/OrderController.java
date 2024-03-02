package com.buffet.buffet.controller.order;

import com.buffet.buffet.controller.order.orderDto.OrderDTO;
import com.buffet.buffet.services.orders.OrderService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"*"})
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping(value = "/addOrder", produces = "application/json")
    public ResponseEntity<CustomResponse> addOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return orderService.register(orderDTO);
    }
}
