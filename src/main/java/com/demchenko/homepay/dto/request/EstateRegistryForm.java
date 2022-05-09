package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

public record EstateRegistryForm(@Positive Long userId, @Positive Long cityId, @Positive Long streetId,
                                 @Positive Integer houseNumber, @PositiveOrZero Integer flatNumber) implements Serializable {

}
