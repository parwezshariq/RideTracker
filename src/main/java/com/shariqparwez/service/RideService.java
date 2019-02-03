package com.shariqparwez.service;

import java.util.List;

import com.shariqparwez.model.Ride;

public interface RideService {

	List<Ride> getRides();

	Ride createRide(Ride ride);

	Ride getRide(int id);

	Ride updateRide(Ride ride);

	void batch();

	void deleteRide(Integer id);

}