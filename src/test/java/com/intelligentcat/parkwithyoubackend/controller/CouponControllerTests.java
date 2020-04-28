package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.service.CouponService;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponControllerTests {
	@Autowired
	CouponService couponService;

	@Before
	public void setUp() {
		RestAssuredMockMvc.standaloneSetup(new CouponController(couponService));
	}

	@Test
	public void should_return_200_when_activate_coupon() {
		MockMvcResponse response = given()
						.contentType(ContentType.JSON)
						.when()
						.get("/coupons?coupon=MQ==");

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

}