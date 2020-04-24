package com.intelligentcat.parkwithyoubackend;

import com.intelligentcat.parkwithyoubackend.controller.ParkingLotBookingAPI;
import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
public class ParkingLotBookingAPITests {
    @Before
    public void setUp(){
        RestAssuredMockMvc.standaloneSetup(new ParkingLotBookingAPI());
    }

    @Test
    public void should_get_receipt_when_sent_booking_request(){
        OrderRequest orderRequest = new OrderRequest(12312);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(orderRequest)
                .when()
                .post("/parking-lots/123/booking");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}
