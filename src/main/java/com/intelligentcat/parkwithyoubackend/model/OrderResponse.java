package com.intelligentcat.parkwithyoubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    String timeStamp;
    Integer orderId;
    Integer customerId;
    Integer parkingLotId;
    Integer parkingPlaceId;
    String parkingPlaceName;
    String startTime;
    Integer duration;
}
