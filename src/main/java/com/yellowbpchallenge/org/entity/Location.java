package com.yellowbpchallenge.org.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Location implements Serializable {
	private static final long serialVersionUID = -952556188559074235L;
	
	@Id
	private Long Id;
	@Column(name = "LocationAddressLine1")
	private String LocationAddressLine1;
	@Column(name = "LocationAddressLine2")
	private String LocationAddressLine2;
	@Column(name= "LocationCity")
	private String LocationCity;
	@Column(name= "LocationStateProvinceRegion")
	private String LocationStateProvinceRegion;
	@Column(name= "LocationZipPostalCode")
	private String LocationZipPostalCode;
	@Column(name= "LocationCountryString")
	private String LocationCountryString;
	@Column(name= "Latitude")
	private float latitude;
	@Column(name= "Longitude")
	private float longitude;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getLocationAddressLine1() {
		return LocationAddressLine1;
	}
	public void setLocationAddressLine1(String locationAddressLine1) {
		LocationAddressLine1 = locationAddressLine1;
	}
	public String getLocationAddressLine2() {
		return LocationAddressLine2;
	}
	public void setLocationAddressLine2(String locationAddressLine2) {
		LocationAddressLine2 = locationAddressLine2;
	}
	public String getLocationCity() {
		return LocationCity;
	}
	public void setLocationCity(String locationCity) {
		LocationCity = locationCity;
	}
	public String getLocationStateProvinceRegion() {
		return LocationStateProvinceRegion;
	}
	public void setLocationStateProvinceRegion(String locationStateProvinceRegion) {
		LocationStateProvinceRegion = locationStateProvinceRegion;
	}
	public String getLocationZipPostalCode() {
		return LocationZipPostalCode;
	}
	public void setLocationZipPostalCode(String locationZipPostalCode) {
		LocationZipPostalCode = locationZipPostalCode;
	}
	public String getLocationCountryString() {
		return LocationCountryString;
	}
	public void setLocationCountryString(String locationCountryString) {
		LocationCountryString = locationCountryString;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitud) {
		this.latitude = latitud;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float logitud) {
		this.longitude = logitud;
	}
	
	public Object[] toObjectArray() {
	    return new Object[] { getLocationAddressLine1(), getLatitude(), getLongitude()};
	}
	
	@Override
	public String toString() {
		return "Location [Id=" + Id + ", LocationAddressLine1=" + LocationAddressLine1 + ", LocationAddressLine2="
				+ LocationAddressLine2 + ", LocationCity=" + LocationCity + ", LocationStateProvinceRegion="
				+ LocationStateProvinceRegion + ", LocationZipPostalCode=" + LocationZipPostalCode
				+ ", LocationCountryString=" + LocationCountryString + ", latitud=" + latitude + ", logitud=" + longitude
				+ "]";
	}
	
	
}
