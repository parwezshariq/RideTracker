package com.shariqparwez.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shariqparwez.model.Ride;
import com.shariqparwez.repository.util.RideRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	@Override
	public List<Ride> getRides() {
		List <Ride> rides = JdbcTemplate.query("select * from ride", new RideRowMapper());
		return rides;
	}

/*	@Override
	public Ride createRide(Ride ride) {
		//JdbcTemplate.update("insert into ride (name, duration) values (?,?)", 
		//		ride.getName(), ride.getDuration());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		JdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("insert into ride (name, duration) values (?,?)", new String[] {"id"});
				ps.setString(1, ride.getName());
				ps.setInt(2, ride.getDuration());
				return ps;
			}
		}, keyHolder);
		
		Number id = keyHolder.getKey();
		return getRide(id.intValue());
		
	}*/

	private Ride getRide(int id) {
		Ride ride = JdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
		
		return ride;
	}
	
	/*@Override
	public Ride createRide(Ride ride) {
		JdbcTemplate.update("insert into ride (name, duration) values (?,?)", 
				ride.getName(), ride.getDuration());
		
		return null;
	}*/
	
	@Override
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
				
		return getRide(key.intValue());
	}
	
}
