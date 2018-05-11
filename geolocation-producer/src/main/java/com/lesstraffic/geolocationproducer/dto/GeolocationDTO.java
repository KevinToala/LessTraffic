package com.lesstraffic.geolocationproducer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class GeolocationDTO {
    private @NotNull BigDecimal latitude;
    private @NotNull BigDecimal longitude;
}
