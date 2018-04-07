package com.lesstraffic.core.model.carto;

import java.util.List;

import com.lesstraffic.core.model.Geolocalization;

public class CartoGeometry {
	private String type;
	private List<Geolocalization> coordinates;
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public List<Geolocalization> getCoordinates(){
		return coordinates;
	}
	
	public void setCoordinates(List<Geolocalization> coordinates){
		this.coordinates = coordinates;
	}
}
