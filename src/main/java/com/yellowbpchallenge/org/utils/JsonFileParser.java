package com.yellowbpchallenge.org.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.yellowbpchallenge.org.entity.Location;

public class JsonFileParser {

	private final String filePath = "../YellowBPChallenge/src/main/resources/static/locations.json";
	private Float[] coords;
	private String mAdress;

	public List<Location> JsonParser() {
		Gson myGson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonReader reader = null;
		try {
			reader = new JsonReader(new InputStreamReader(new FileInputStream(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JsonArray locationArray = jsonParser.parse(reader).getAsJsonArray();
		List<Location> locations = new ArrayList<>();
		int nr = 0;
		for (JsonElement element : locationArray) {
			Location location = myGson.fromJson(element, Location.class);
			String adress = location.getLocationAddressLine1();
			adress += ", " +location.getLocationAddressLine2();
			adress += ", " +location.getLocationCountryString();
			adress += ", " +location.getLocationCity();
			adress += ", " +location.getLocationZipPostalCode();
			if (!adress.equals(mAdress) || mAdress.isEmpty()) {
				getCoordinates(adress);
			} 			
			if (coords != null) {
				nr++;
				location.setLatitude(coords[0]);
				location.setLongitude(coords[1]);
				System.out.println("GOT COORDINATES " + coords[0] + ", " + coords[1] + " -- locations scanned: " + nr);
			}
			if(adress.equals(mAdress)){
				System.out.println("Identical adress setting same coordinates");
			}
		locations.add(location);
		mAdress = adress;
	}
	return locations;
	}
	
	private void getCoordinates(String adress) {
		Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(adress).getGeocoderRequest();
		GeocodeResponse geocoderResponse = null;
		int millis = 200;
		try {
			do {
				geocoderResponse = geocoder.geocode(geocoderRequest);
				Thread.sleep(millis);
				millis += 100;
			} while (geocoderResponse.getStatus() == GeocoderStatus.OVER_QUERY_LIMIT);
			if (geocoderResponse.getStatus() == GeocoderStatus.ZERO_RESULTS) {
				System.out.println(geocoderResponse.getStatus().toString() + " - Passed a non-existent address");
			}
			if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
				System.out.println("Geocoder response: " + geocoderResponse.getStatus().toString());
				GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();
				LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();
				coords = new Float[2];
				coords[0] = latitudeLongitude.getLat().floatValue();
				coords[1] = latitudeLongitude.getLng().floatValue();
			}
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
