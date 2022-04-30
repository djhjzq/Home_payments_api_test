package com.demchenko.homepay.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRegistryForm {

    private String name;

    private String invoiceType;

    private Long userId;
}
