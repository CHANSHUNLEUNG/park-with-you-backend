package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-lots/{parkingLotId}/booking")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping
    public OrderResponse addNewBooking(@PathVariable("parkingLotId") String parkingLotId,
                                       @RequestBody OrderRequest orderRequest) {
        System.out.println(String.format("%s - %s", parkingLotId, orderRequest.getCustomerId()));
        return bookingService.addNewBooking();
    }
}