package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public record UserUpdateForm(@Positive Long userId, @NotBlank String firstName, @NotBlank String lastName,
                             @Email String email) implements Serializable {
}
