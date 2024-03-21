package com.buffet.buffet.controller.order;

import com.buffet.buffet.controller.order.orderdto.OrderDTO;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
import com.buffet.buffet.services.orders.OrderService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class OrderController {
    private final OrderService orderService;
    @Autowired

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/addOrder", produces = "application/json")
        public ResponseEntity<CustomResponse> addOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return orderService.register(orderDTO);
    }
    @PostMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<CustomResponse> updateStatus(@Valid @RequestBody UpdateStatus updateStatus){
        return orderService.updateStatus(updateStatus);
    }
    @PostMapping(value = "/getOrderByNumOrder/{num}", produces = "application/json")
    public ResponseEntity<CustomResponse> getOrderByNumOrder(@PathVariable("num") String numOrder) {
        return orderService.findByNumOrder(numOrder);
    }
    @PostMapping(value = "/getAllOrderByEmail/{email}", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllOrderByEmail(@PathVariable("email") String email) {
        return orderService.findAllByEmailOrder(email);
    }
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllOrders() {
        return orderService.findAllOrders();
    }
    @GetMapping(value = "/getCountOrders", produces = "application/json")
    public ResponseEntity<CustomResponse> getCountOrders() {
        return orderService.countAllOrdersRequired();
    }
    @GetMapping(value = "/getAllOrdersRequired", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllOrdersRequired() {
        return orderService.findAllOrdersRequired();
    }
}
