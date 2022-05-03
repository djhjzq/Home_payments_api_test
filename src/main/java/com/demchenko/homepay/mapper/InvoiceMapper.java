package com.demchenko.homepay.mapper;

import com.demchenko.homepay.dto.response.InvoiceDto;
import com.demchenko.homepay.entity.Invoice;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InvoiceMapper {
    @Mapping(source = "estateId", target = "estate.id")
    Invoice invoiceDtoToInvoice(InvoiceDto invoiceDto);

    @Mapping(source = "estate.id", target = "estateId")
    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    @Mapping(source = "estateId", target = "estate.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInvoiceFromInvoiceDto(InvoiceDto invoiceDto, @MappingTarget Invoice invoice);
}
