package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.ShareLink;
import com.intelligentcat.parkwithyoubackend.service.CouponService;
import io.restassured.http.ContentType;
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

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponControllerTests {
    CouponService couponService;

    @Before
    public void setUp() {
        couponService = Mockito.mock(CouponService.class);
        RestAssuredMockMvc.standaloneSetup(new CouponController(couponService));
    }

    @Test
    public void should_return_shared_link_when_get_by_customer_id_order_id() {
        doReturn(new ShareLink()).when(couponService).getShareLink(any(), any());

        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/coupons/share-link?customerId=1&orderId=1");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void should_return_200_when_activate_coupon() {
        MockMvcResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/coupons?coupon=MTAwMA==");

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

}