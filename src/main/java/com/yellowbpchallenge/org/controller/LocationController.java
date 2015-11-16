package com.yellowbpchallenge.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;
import com.yellowbpchallenge.org.service.LocationService;

@Controller
public class LocationController {

	private LocationService locationService;

	@Autowired
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMap(Model model) {
		if (locationService.getAll().isEmpty()) {
			locationService.saveLocations();
		}
		Object[][] locationArray = locationService.getLocationsAsObjectArray();
		model.addAttribute("locations", new Gson().toJson(locationArray));
		return "index";
	}
}
