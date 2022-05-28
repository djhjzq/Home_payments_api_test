package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.EstateResponse;
import com.demchenko.homepay.entity.Estate;
import com.demchenko.homepay.entity.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EstateMapper {
    @Mapping(source = "cityId", target = "city.id")
    @Mapping(source = "cityName", target = "city.name")
    @Mapping(source = "streetId", target = "street.id")
    @Mapping(source = "streetName", target = "street.name")
    Estate estateDtoToEstate(EstateResponse estateResponse);

    @InheritInverseConfiguration(name = "estateDtoToEstate")
    @Mapping(target = "userSetIds", expression = "java(userSetToUserSetIds(estate.getUserSets()))")
    EstateResponse estateToEstateDto(Estate estate);

    @InheritConfiguration(name = "estateDtoToEstate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEstateFromEstateDto(EstateResponse estateResponse, @MappingTarget Estate estate);

    default Set<Long> userSetToUserSetIds(Set<User> userSet) {
        return userSet.stream().map(User::getId).collect(Collectors.toSet());
    }
}
