package com.yellowbpchallenge.org.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yellowbpchallenge.org.entity.Location;
import com.yellowbpchallenge.org.repository.LocationRepository;
import com.yellowbpchallenge.org.utils.JsonFileParser;



@Component
public class LocationServiceImpl implements LocationService {

	private LocationRepository locationRepository;
	
	@Autowired
	public LocationServiceImpl(LocationRepository locationRepository){
		this.locationRepository = locationRepository;
	}
	
	@Override
	public void saveLocations() {
	   JsonFileParser jsonFileParser = new JsonFileParser();
	   List<Location> locations = new ArrayList<Location>();
	   try {
		locations = jsonFileParser.JsonParser();
	} catch (Exception e) {
		e.printStackTrace();
	}
	    for(Location location : locations){	
	    	locationRepository.save(location);
	    }
	}
	
	@Override
	public List<Location> getAll() {
		return locationRepository.findAll();
	}

	@Override
	public Object[][] getLocationsAsObjectArray() {
		List<Location> locationList = locationRepository.findAll();
		Object[][] locationArray = new Object[locationList.size()][locationList.size()];
		for (int i = 0; i < locationList.size(); i++) {
		    locationArray[i] = locationList.get(i).toObjectArray();
		}	
		return locationArray;
	}

}
