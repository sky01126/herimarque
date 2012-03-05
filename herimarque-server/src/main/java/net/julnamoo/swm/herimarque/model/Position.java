package net.julnamoo.swm.herimarque.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

@Field
public class Position implements Serializable{

	String latitude;
	String longitude;
	
	public Position(){}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
}
