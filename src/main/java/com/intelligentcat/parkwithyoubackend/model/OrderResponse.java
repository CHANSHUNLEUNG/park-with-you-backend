package com.intelligentcat.parkwithyoubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    Long timeStamp;
    Integer orderId;
    Integer customerId;
    String parkingLotName;
    String parkingPlaceName;
    Long startTime;
    Long endTime;
}
