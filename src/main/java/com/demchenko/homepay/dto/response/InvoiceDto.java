package com.demchenko.homepay.dto.response;

import com.demchenko.homepay.entity.InvoiceType;

import java.io.Serializable;

public record InvoiceDto(Long id, String name, InvoiceType invoiceType,
                         Long estateId) implements Serializable {
}
