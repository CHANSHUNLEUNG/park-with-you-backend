package com.intelligentcat.parkwithyoubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String timeStamp;
    private Integer orderId;
    private Integer customerId;
    private Integer parkingPlaceId;
    private String startTime;
    private Integer duration;
}
