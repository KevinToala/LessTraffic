package com.lesstraffic.core.services;

import java.util.List;

import com.lesstraffic.core.model.Geolocalization;

public interface GeolocalizationService {
	List<Geolocalization> getNodes();
	Geolocalization insertNode(Geolocalization geolocalization);
}
