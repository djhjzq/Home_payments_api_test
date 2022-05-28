package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.UserResponse;
import com.demchenko.homepay.entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserResponse userResponse);

    UserResponse userToUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUserDto(UserResponse userResponse, @MappingTarget User user);
}
