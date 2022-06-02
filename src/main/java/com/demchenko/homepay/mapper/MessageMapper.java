package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.MessageResponseText;
import com.demchenko.homepay.entity.Message;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userFirstName", target = "user.firstName")
    @Mapping(source = "userLastName", target = "user.lastName")
    @Mapping(source = "userAddressId", target = "userAddress.id")
    @Mapping(source = "userAddressFirstName", target = "userAddress.firstName")
    @Mapping(source = "userAddressLastName", target = "userAddress.lastName")
    Message messageResponseTextToMessage(MessageResponseText messageResponseText);

    @InheritInverseConfiguration(name = "messageResponseTextToMessage")
    MessageResponseText messageToMessageResponseText(Message message);

    @InheritConfiguration(name = "messageResponseTextToMessage")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMessageFromMessageResponseText(MessageResponseText messageResponseText, @MappingTarget Message message);
}
