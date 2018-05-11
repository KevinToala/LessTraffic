package com.lesstraffic.geolocationservice.model;

import lombok.*;

import java.math.BigDecimal;

@Data
public class Geolocation {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
