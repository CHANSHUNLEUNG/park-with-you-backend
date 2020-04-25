package com.intelligentcat.parkwithyoubackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingPlace {
    Integer id;
    String name;
    Integer parking_lot_id;
    String status;

    @Override
    public String toString(){
        return String.format("{id: %d, name: %s, parking_lot_id: %d, status: %s}", id, name, parking_lot_id, status);
    }
}