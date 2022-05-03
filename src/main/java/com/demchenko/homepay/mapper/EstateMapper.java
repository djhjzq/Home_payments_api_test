package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.EstateDto;
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
    Estate estateDtoToEstate(EstateDto estateDto);

    @InheritInverseConfiguration(name = "estateDtoToEstate")
    @Mapping(target = "userSetIds", expression = "java(userSetToUserSetIds(estate.getUserSets()))")
    EstateDto estateToEstateDto(Estate estate);

    @InheritConfiguration(name = "estateDtoToEstate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEstateFromEstateDto(EstateDto estateDto, @MappingTarget Estate estate);

    default Set<Long> userSetToUserSetIds(Set<User> userSet) {
        return userSet.stream().map(User::getId).collect(Collectors.toSet());
    }
}
