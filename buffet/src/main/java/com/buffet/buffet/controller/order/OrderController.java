package com.buffet.buffet.controller.order;

import com.buffet.buffet.controller.order.orderDto.OrderDTO;
import com.buffet.buffet.model.UpdateStatus.UpdateStatus;
import com.buffet.buffet.services.orders.OrderService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;

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
    @PostMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<CustomResponse> updateStatus(@Valid @RequestBody UpdateStatus updateStatus){
        return orderService.updateStatus(updateStatus);
    }
    @PostMapping(value = "/getOrderByNumOrder", produces = "application/json")
    public ResponseEntity<CustomResponse> getOrderByNumOrder(@RequestBody String numOrder) {
        return orderService.findByNumOrder(numOrder);
    }

}
