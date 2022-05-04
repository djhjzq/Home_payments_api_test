package com.demchenko.homepay.dto.request;


import java.io.Serializable;
import java.math.BigDecimal;


public record PaymentRegistryForm(Long invoiceId, Long userId,
                                  BigDecimal amount) implements Serializable {
}
