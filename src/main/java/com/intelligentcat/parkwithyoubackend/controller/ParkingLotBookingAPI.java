package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-lots/{parkingLotId}/booking")
public class ParkingLotBookingAPI {

    @PostMapping
    public OrderResponse addNewBooking(@PathVariable("parkingLotId") String parkingLotId,
                                       @RequestBody OrderRequest orderRequest) {
        System.out.println(String.format("%s - %s", parkingLotId, orderRequest.getCustomerId()));
        return new OrderResponse(new Long(1587714699), 12312, 12312, "Haha", "hehe", new Long(587714699), new Long(1587724699));
    }
}
