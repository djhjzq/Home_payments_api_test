package com.demchenko.homepay.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public record InvoiceRegistryForm(@NotBlank String name, @NotBlank String invoiceType,
                                  @Positive Long estateId) implements Serializable {

}
