package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.ParkingLot;
import com.intelligentcat.parkwithyoubackend.service.ParkingLotService;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
public class ParkingLotControllerTests {
	@Autowired
	ParkingLotService parkingLotService;

	@Before
	public void setUp() {
		RestAssuredMockMvc.standaloneSetup(new ParkingLotController(parkingLotService));
	}

//	@Test
//	public void should_get_all_parking_lot_list_when_request_parking_lot() {
//		MockMvcResponse response = given()
//						.contentType(ContentType.JSON)
//						.when()
//						.get("/parking-lots");
//
//		List<ParkingLot> parkingLots = response
//						.getBody()
//						.as(
//										new TypeRef<List<ParkingLot>>() {
//											@Override
//											public Type getType() {
//												return super.getType();
//											}
//										}
//						);
//
//		System.out.println(parkingLots);
//		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//	}
}