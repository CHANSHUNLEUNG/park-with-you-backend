package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.model.CustomerRequest;
import com.intelligentcat.parkwithyoubackend.service.CustomerService;
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
public class CustomerControllerTests {
	@Autowired
	CustomerService customerService;

	@Before
	public void setUp() {
		RestAssuredMockMvc.standaloneSetup(new CustomerController(customerService));
	}

	@Test
	public void should_login_success_with_correct_customer_username_and_password() {
		MockMvcResponse response = given()
						.contentType(ContentType.JSON)
						.body(new CustomerRequest("4f31fa50e5bd5ff45684e560fc24aeee527a43739ab611c49c51098a33e2b469"))
						.when()
						.post("/customers/Matt/login");

		Customer customer = response
						.getBody()
						.as(Customer.class);

		System.out.println(customer);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}
}