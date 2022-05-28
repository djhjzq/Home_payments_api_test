package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.CityResponse;
import com.demchenko.homepay.entity.City;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CityMapper {
    City cityResponseToCity(CityResponse cityResponse);

    CityResponse cityToCityResponse(City city);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCityFromCityResponse(CityResponse cityResponse, @MappingTarget City city);
}
