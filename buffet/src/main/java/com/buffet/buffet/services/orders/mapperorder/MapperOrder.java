package com.buffet.buffet.services.orders.mapperorder;

import com.buffet.buffet.controller.order.orderdto.OrderDTO;
import com.buffet.buffet.model.orders.Order;

import java.util.List;
import java.util.stream.Collectors;

public class MapperOrder {
    public static List<OrderDTO> mapOrderToOrderDTOs(List<Order> orderList) {
        return orderList.stream()
                .map(MapperOrder::mapOrderToOrderDTO)
                .collect(Collectors.toList());
    }


    public static OrderDTO mapOrderToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setNumOrder(order.getNumOrder());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderPrice(order.getOrderPrice());
        orderDTO.setStatus(order.getStatus().getStatusName());
        orderDTO.setUserEmail(order.getUserAccount().getEmail());
        orderDTO.setPackageName(order.getServicePackage().getPackageName());
        return orderDTO;
    }
}
