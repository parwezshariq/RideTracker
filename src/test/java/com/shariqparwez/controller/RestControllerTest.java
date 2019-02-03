package com.shariqparwez.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.shariqparwez.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout=10000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
	
	@Test(timeout=10000)
	public void testCreateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = new Ride();
		//ride.setName("Bobsled Trail Ride");
		//ride.setDuration(35);
		
		//ride.setName("Willow Trail Ride");
		//ride.setDuration(30);
		
		//ride.setName("Sagebrush Trail");
		//ride.setDuration(33);
		
		ride.setName("Yellow Fork Trail");
		ride.setDuration(36);
		
		//restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
		restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride, Ride.class);
		
		System.out.println("Ride: " + ride);
	
	}
	
	/*@Test(timeout=5000)
	public void testCreateRideWithSimpleJDBCInsert() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = new Ride();
		
		ride.setName("Round Valley Ride");
		ride.setDuration(38);
		
		restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
	}*/
	
	@Test(timeout=10000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
	
		System.out.println("Ride name: " + ride.getName());
		
	}
	
	@Test(timeout=10000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		ride.setDuration(ride.getDuration() + 1);
		restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
		
		System.out.println("Ride name: " + ride.getName());
		
	}
	
	@Test(timeout=10000)
	public void testBatchUpdate() {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Object.class);
	
		
	}
	
	@Test(timeout=10000)
	public void testDelete() {
		RestTemplate restTemplate = new RestTemplate();
		
		//restTemplate.delete("http://localhost:8080/ride_tracker/delete/5");
		restTemplate.delete("http://localhost:8080/ride_tracker/delete/4");
	
		
	}
	
	@Test(timeout=10000)
	public void testException() {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Ride.class);
	}
		
	
}
