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
	Double unitPrice;
	Integer capacity;
	Integer availableCount;
	String region;
}
