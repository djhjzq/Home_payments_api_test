package com.demchenko.homepay.dto.request;


import java.io.Serializable;

public record InvoiceRegistryForm(String name, String invoiceType, Long estateId) implements Serializable {

}
