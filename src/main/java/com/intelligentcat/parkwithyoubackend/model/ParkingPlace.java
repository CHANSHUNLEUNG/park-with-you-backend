package com.intelligentcat.parkwithyoubackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParkingPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer parkingLotId;
    String status;

    @Override
    public String toString() {
        return String.format("{id: %d, name: %s, parking_lot_id: %d, status: %s}", id, name, parkingLotId, status);
    }
}
