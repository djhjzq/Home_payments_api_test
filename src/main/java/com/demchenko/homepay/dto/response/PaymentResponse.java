package com.demchenko.homepay.dto.response;

import com.demchenko.homepay.entity.InvoiceType;
import com.demchenko.homepay.entity.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public record PaymentResponse(Long id, Date createdOn, Long invoiceId,
                              String invoiceName, InvoiceType invoiceInvoiceType,
                              Long userId, String userFirstName, String userLastName,
                              BigDecimal amount,
                              PaymentStatus paymentStatus) implements Serializable {
}
