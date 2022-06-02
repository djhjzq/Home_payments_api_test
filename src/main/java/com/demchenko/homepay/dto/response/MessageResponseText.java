package com.demchenko.homepay.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageResponseText implements Serializable {
    private final Long id;
    private final Long userId;
    private final String userFirstName;
    private final String userLastName;
    private final Long userAddressId;
    private final String userAddressFirstName;
    private final String userAddressLastName;
    private final String text;
}
