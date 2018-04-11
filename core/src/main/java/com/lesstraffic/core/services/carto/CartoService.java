package com.lesstraffic.core.services.carto;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
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
	public Geolocalization insertNode(Geolocalization geolocalization){
		String insert = String.format(
				"insert into penetracion_final_uio_copy_1(the_geom, username, insert_date)" +
						" values(ST_SetSRID(ST_Point(%s, %s), 4326), 'ADMIN', '%s')",
				geolocalization.getLongitude(), geolocalization.getLatitude(),
				Timestamp.valueOf(LocalDateTime.now())
		);

		try {
			restTemplate.postForObject(buildUrl(insert), null, String.class);
		}
		catch (HttpStatusCodeException exception){
			System.err.println(exception.getResponseBodyAsString());
			exception.printStackTrace();
			throw exception;
		}
		
		return geolocalization;
	}

	private URI buildUrl(String query){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_REST_SERVICE)
				.queryParam("q", query)
				.queryParam("api_key", CARTO_API_KEY);

		return builder.build(false).toUri();
	}
}
