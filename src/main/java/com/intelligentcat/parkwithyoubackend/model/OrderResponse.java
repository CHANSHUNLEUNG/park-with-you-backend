package com.intelligentcat.parkwithyoubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    String time_stamp;
    Integer order_id;
    Integer customer_id;
    Integer parking_lot_id;
    Integer parking_place_id;
    String parking_place_name;
    String start_time;
    Integer duration;
}
