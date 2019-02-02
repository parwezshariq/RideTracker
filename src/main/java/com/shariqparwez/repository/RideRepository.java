package com.shariqparwez.repository;

import java.util.List;

import com.shariqparwez.model.Ride;

public interface RideRepository {

	List<Ride> getRides();

	Ride createRide(Ride ride);

	Ride getRide(int id);

	Ride updateRide(Ride ride);

	void updateRides(List<Object[]> pairs);

}