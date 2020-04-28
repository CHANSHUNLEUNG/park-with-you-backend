package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.controller.OrderController;
import com.intelligentcat.parkwithyoubackend.model.OrderDetail;
import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.service.OrderService;
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

    @Test
    public void should_get_order_detail_lot_list_by_customer() {
        MockMvcResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parking-orders/customers/1");

        List<OrderDetail> orderDetails = response
                .getBody()
                .as(
                        new TypeRef<List<OrderDetail>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        }
                );

        System.out.println(orderDetails);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assert.assertEquals(2, orderDetails.size());
    }
}
