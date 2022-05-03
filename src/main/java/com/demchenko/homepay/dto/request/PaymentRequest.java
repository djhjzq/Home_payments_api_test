package com.demchenko.homepay.dto.request;


import java.io.Serializable;
import java.math.BigDecimal;


public record PaymentRequest(Long invoiceId, Long userId,
                             BigDecimal amount) implements Serializable {
}
