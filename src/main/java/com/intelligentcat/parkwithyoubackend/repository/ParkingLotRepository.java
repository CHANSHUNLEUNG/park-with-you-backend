package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.model.ParkingLot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ParkingLotRepository {
	private JdbcTemplate jdbcTemplate;

	private class ParkingLotRowMapper implements RowMapper<ParkingLot> {
		@Override
		public ParkingLot mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			ParkingLot parkingLot = new ParkingLot();
			parkingLot.setId(resultSet.getInt("id"));
			parkingLot.setName(resultSet.getString("name"));
			parkingLot.setAddress(resultSet.getString("address"));
			parkingLot.setUnit_price(resultSet.getDouble("unit_price"));
			parkingLot.setCapacity(resultSet.getInt("capacity"));
			parkingLot.setAvailable_count(resultSet.getInt("available_count"));
			parkingLot.setRegion(resultSet.getString("region"));
			return parkingLot;
		}
	}

	public List<ParkingLot> findAll() {
		final String sql = "select * from parking_lot";
		return jdbcTemplate.query(sql, new ParkingLotRowMapper());
	}
}
