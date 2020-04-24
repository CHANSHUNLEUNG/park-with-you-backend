package com.intelligentcat.parkwithyoubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
	Integer id;
	String name;
	String address;
	Double unit_price;
	Integer capacity;
	Integer available_count;
	String region;
}
