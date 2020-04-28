package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.model.ParkingLot;
import com.intelligentcat.parkwithyoubackend.service.ParkingLotService;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotControllerTests {
    ParkingLotService parkingLotService;

    @Before
    public void setUp() {
        parkingLotService = Mockito.mock(ParkingLotService.class);
        RestAssuredMockMvc.standaloneSetup(new ParkingLotController(parkingLotService));
    }

    @Test
    public void should_get_all_parking_lot_list_when_request_parking_lot() {
        MockMvcResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parking-lots");

        List<ParkingLot> parkingLots = response
                .getBody()
                .as(
                        new TypeRef<List<ParkingLot>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        }
                );

        System.out.println(parkingLots);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void should_get_one_tai_po_parking_lot_list_when_request_parking_lot_with_tai_po_request_param() {
        MockMvcResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/parking-lots?region=Tai Po");

        List<ParkingLot> parkingLots = response
                .getBody()
                .as(
                        new TypeRef<List<ParkingLot>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        }
                );

        System.out.println(parkingLots);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void should_get_receipt_when_sent_booking_request() {
        doReturn(new OrderResponse()).when(parkingLotService).addNewBooking(any(), any());
        OrderRequest orderRequest;
        orderRequest = new OrderRequest(1, "2020-04-26 23:00:00", 3600);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(orderRequest)
                .when()
                .post("/parking-lots/3/booking");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
}