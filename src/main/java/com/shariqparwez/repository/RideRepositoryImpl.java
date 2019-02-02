package com.shariqparwez.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.shariqparwez.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	@Override
	public List<Ride> getRides() {
		List <Ride> rides = JdbcTemplate.query("select * from ride", new RowMapper<Ride>() {

			@Override
			public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ride ride = new Ride();
				ride.setId(rs.getInt("id"));
				ride.setName(rs.getString("name"));
				ride.setDuration(rs.getInt("duration"));
				return ride;
			}
		});
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {
		JdbcTemplate.update("insert into ride (name, duration) values (?,?)", 
				ride.getName(), ride.getDuration());
		
		return null;
	}
	
	/*@Override
	public Ride createRide(Ride ride) {
		//Using SimpleJdbcInsert
		SimpleJdbcInsert insert = new SimpleJdbcInsert(JdbcTemplate);
		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");
		insert.setTableName("ride");
		insert.setColumnNames(columns);
		
		Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());		
		
		insert.setGeneratedKeyName("id");
		Number key = insert.executeAndReturnKey(data);
		
		System.out.println(key);
		
		return null;
	}*/
	
}
