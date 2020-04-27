package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-lots/{parkingLotId}/booking")
public class OrderController {
    private BookingService bookingService;

    @Autowired
    public OrderController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public OrderResponse addNewBooking(@PathVariable("parkingLotId") Integer parkingLotId,
                                       @RequestBody OrderRequest orderRequest) {
        return bookingService.addNewBooking(parkingLotId, orderRequest);
    }
}
