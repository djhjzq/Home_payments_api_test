package com.demchenko.homepay.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstateResponse {

    private Long id;

    private String cityName;

    private String streetName;

    private Integer houseNumber;

    private Integer flatNumber;

}
