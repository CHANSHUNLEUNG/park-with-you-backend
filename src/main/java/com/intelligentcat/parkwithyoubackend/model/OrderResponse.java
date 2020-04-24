package com.intelligentcat.parkwithyoubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    Timestamp timeStamp;
    Integer orderId;
    Integer customerId;
    String parkingLotName;
    String parkingPlaceName;
    Timestamp startTime;
    Timestamp endTime;
}
