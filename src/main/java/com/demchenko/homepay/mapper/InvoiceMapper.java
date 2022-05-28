package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.InvoiceResponse;
import com.demchenko.homepay.entity.Invoice;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(source = "estateId", target = "estate.id")
    Invoice invoiceDtoToInvoice(InvoiceResponse invoiceResponse);

    @Mapping(source = "estate.id", target = "estateId")
    InvoiceResponse invoiceToInvoiceDto(Invoice invoice);

    @Mapping(source = "estateId", target = "estate.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInvoiceFromInvoiceDto(InvoiceResponse invoiceResponse, @MappingTarget Invoice invoice);
}
