package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.PaymentDto;
import com.demchenko.homepay.entity.Payment;
import com.demchenko.homepay.entity.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "invoiceId", target = "invoice.id")
    @Mapping(source = "invoiceName", target = "invoice.name")
    @Mapping(source = "invoiceInvoiceType", target = "invoice.invoiceType")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userFirstName", target = "user.firstName")
    @Mapping(source = "userLastName", target = "user.lastName")
    Payment paymentDtoToPayment(PaymentDto paymentDto);

    @InheritInverseConfiguration(name = "paymentDtoToPayment")
    PaymentDto paymentToPaymentDto(Payment payment);

    @InheritConfiguration(name = "paymentDtoToPayment")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentFromPaymentDto(PaymentDto paymentDto, @MappingTarget Payment payment);

    default Set<Long> userSetToUserSetIds(Set<User> userSet) {
        return userSet.stream().map(User::getId).collect(Collectors.toSet());
    }
}
