package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.controller.OrderController;
import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.service.OrderService;
import io.restassured.http.ContentType;
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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTests {
    @Autowired
    OrderService orderService;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(new OrderController(orderService));
    }

    @Test
    public void should_get_receipt_when_sent_booking_request() {
        OrderRequest orderRequest;
        orderRequest = new OrderRequest(1, "2020-04-26 23:00:00", 3600);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(orderRequest)
                .when()
                .post("/parking-lots/1/booking");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}
