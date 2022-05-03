package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.UserDto;
import com.demchenko.homepay.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

}
