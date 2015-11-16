package com.yellowbpchallenge.org.service;

import java.util.List;

import com.yellowbpchallenge.org.entity.Location;

public interface LocationService {
	public void saveLocations();

	public List<Location> getAll();
	
	public Object[][] getLocationsAsObjectArray();
}