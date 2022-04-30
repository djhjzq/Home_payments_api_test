package com.demchenko.homepay.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceResponse {

    private Long id;

    private String name;

    private String invoiceType;

    private Long estateId;
}
