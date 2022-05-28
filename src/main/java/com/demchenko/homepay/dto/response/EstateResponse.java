package com.demchenko.homepay.dto.response;

import java.io.Serializable;
import java.util.Set;

public record EstateResponse(Long id, Integer houseNumber, Integer flatNumber,
                             Set<Long> userSetIds, Long cityId, String cityName,
                             Long streetId, String streetName) implements Serializable {
}
