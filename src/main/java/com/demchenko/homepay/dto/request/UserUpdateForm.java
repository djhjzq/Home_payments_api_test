package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record UserUpdateForm(@Positive Long userId, @Size(min = 2, max = 20) String firstName, @Size(min = 2, max = 20) String lastName,
                             @Email String email) implements Serializable {
}
