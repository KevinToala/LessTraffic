package com.lesstraffic.geolocationservice.services.carto;

import com.lesstraffic.geolocationservice.dto.GeolocationDTO;
import com.lesstraffic.geolocationservice.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CartoService implements GeolocationService {
	private static final String URL_REST_SERVICE = "https://kevin09296.carto.com/api/v2/sql";
	private static final String CARTO_API_KEY = "1508f047490d65d5430853114397898064ab2794";
	private static final String CARTO_TABLE_NODES = "penetracion_final_uio_copy_1";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public GeolocationDTO insertNode(GeolocationDTO geolocationDTO){
		String insert = String.format(
				"insert into $CartoTable$(the_geom, username, insert_date)" +
						" values(ST_SetSRID(ST_Point(%s, %s), 4326), 'ADMIN', '%s')",
				geolocationDTO.getLongitude(), geolocationDTO.getLatitude(),
				Timestamp.valueOf(LocalDateTime.now())
		);

		restTemplate.postForObject(buildUrl(insert), null, String.class);
		
		return geolocationDTO;
	}

	private URI buildUrl(String query){
		String finalQuery = query.replace("$CartoTable$", CARTO_TABLE_NODES);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_REST_SERVICE)
				.queryParam("q", finalQuery)
				.queryParam("api_key", CARTO_API_KEY);

		return builder.build(false).toUri();
	}
}
