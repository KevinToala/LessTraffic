package com.lesstraffic.geolocationservice.model;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationDTO {
	private BigDecimal latitude;
	private BigDecimal longitude;
}