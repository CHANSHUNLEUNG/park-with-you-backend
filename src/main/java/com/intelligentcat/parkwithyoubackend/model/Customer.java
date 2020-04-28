package com.intelligentcat.parkwithyoubackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
Integer discountAmount;
    Integer id;
    String name;
    String password;
    String BankAccount;
    Integer availableCouponCount;
    Double discount;
}
