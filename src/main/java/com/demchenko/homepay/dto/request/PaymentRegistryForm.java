package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;


public record PaymentRegistryForm(@Positive Long invoiceId, @Positive Long userId,
                                  @Positive BigDecimal amount) implements Serializable {
}
