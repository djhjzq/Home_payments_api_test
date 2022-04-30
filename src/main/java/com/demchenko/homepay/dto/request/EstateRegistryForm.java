package com.demchenko.homepay.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstateRegistryForm {

    private Long userId;

    private Long cityId;

    private Long streetId;

    private Integer houseNumber;

    private Integer flatNumber;

}
