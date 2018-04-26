package com.lesstraffic.geolocationservice.model;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Geolocation {
	private BigDecimal latitude;
	private BigDecimal longitude;
}
