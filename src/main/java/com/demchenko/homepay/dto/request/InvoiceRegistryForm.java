package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record InvoiceRegistryForm(@Size(min = 2, max = 20) String name, @Size(min = 2, max = 20) String invoiceType,
                                  @Positive Long estateId) implements Serializable {

}
