package com.demchenko.homepay.dto.request;


import java.io.Serializable;

public record EstateRegistryForm(Long userId, Long cityId, Long streetId,
                                 Integer houseNumber, Integer flatNumber) implements Serializable {

}
