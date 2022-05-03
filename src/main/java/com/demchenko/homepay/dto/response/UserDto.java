package com.demchenko.homepay.dto.response;


import java.io.Serializable;
import java.math.BigDecimal;

public record UserDto(Long id, String firstName, String lastName, String email,
                      BigDecimal balance) implements Serializable {
}
