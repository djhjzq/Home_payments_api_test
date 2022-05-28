package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.StreetResponse;
import com.demchenko.homepay.entity.Street;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StreetMapper {
    Street streetResponseToStreet(StreetResponse streetResponse);

    StreetResponse streetToStreetResponse(Street street);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStreetFromStreetResponse(StreetResponse streetResponse, @MappingTarget Street street);
}
