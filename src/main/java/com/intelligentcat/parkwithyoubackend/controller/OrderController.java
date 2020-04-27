package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.*;
import com.intelligentcat.parkwithyoubackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/parking-lots/{parkingLotId}/booking")
    public OrderResponse addNewBooking(@PathVariable("parkingLotId") Integer parkingLotId,
                                       @RequestBody OrderRequest orderRequest) {
        return orderService.addNewBooking(parkingLotId, orderRequest);
    }

    @GetMapping("/parking-orders/customers/{customerId}")
    public List<OrderDetail> getOrderListByCustomer(@PathVariable Integer customerId) {
        return orderService.getOrderListByCustomer(customerId);
    }

    @PostMapping("/parking-orders/{orderId}")
    public Order extendBooking(@PathVariable("orderId") Integer orderId,
                               @RequestBody ExtendOrderRequest extendOrderRequest) {
        return orderService.extendCurrentBooking(orderId, extendOrderRequest);
    }
}
