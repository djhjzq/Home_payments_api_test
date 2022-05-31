package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record EstateRegistryForm(@Positive Long userId, @Size(min = 2, max = 20) String cityName, @Size(min = 2, max = 20)
                                 String streetName,
                                 @Positive Integer houseNumber, @PositiveOrZero Integer flatNumber) implements Serializable {

}
