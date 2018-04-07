package com.lesstraffic.core.services.carto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.lesstraffic.core.model.Geolocalization;
import com.lesstraffic.core.model.carto.CartoGeometry;
import com.lesstraffic.core.services.GeolocalizationService;

@Service
public class CartoService implements GeolocalizationService {
	private static final String URL_REST_SERVICE = "https://kevin09296.carto.com/api/v2/sql";
	private static final String CARTO_API_KEY = "1508f047490d65d5430853114397898064ab2794";
	
	@Autowired
	private RestTemplate restTemplate; 
		
	@Override
	public List<Geolocalization> getNodes(){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_REST_SERVICE)
				.queryParam("q", "SELECT the_geom FROM penetracion_final_uio_copy")
				.queryParam("api_key", CARTO_API_KEY);
	
		String url = builder.toUriString();
		
		List<CartoGeometry> geometries = Arrays.asList(restTemplate.getForObject(url, CartoGeometry[].class));
		
		return geometries.stream()
				.flatMap(geometry -> geometry.getCoordinates().stream())
				.collect(Collectors.toList());
	}

	@Override
	public Geolocalization insertNode(Geolocalization geolocalization){
		String insert = String.format(
				"insert into penetracion_final_uio_copy(the_geom) values(ST_SetSRID(ST_MakePoint('%s', '%s'), 4326))",
				geolocalization.getLongitude(), geolocalization.getLatitude()
		);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_REST_SERVICE)
				.queryParam("q", insert)
				.queryParam("api_key", CARTO_API_KEY);
		
		restTemplate.postForObject(builder.toUriString(), null, String.class);
		
		return geolocalization;
	}
	
}
