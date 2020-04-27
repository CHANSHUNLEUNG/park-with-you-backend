package com.intelligentcat.parkwithyoubackend.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	String orderTime;
	Integer orderId;
	Integer customerId;
	Integer parkingLotId;
	String parkingLotName;
	String address;
	Double unitPrice;
	Integer parkingPlaceId;
	String parkingPlaceName;
	String startParkingTime;
	Integer duration;
}
