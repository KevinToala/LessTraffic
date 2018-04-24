package com.lesstraffic.geolocationproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationDTO {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
