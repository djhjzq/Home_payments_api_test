package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record UserRegistryForm(@NotBlank(message = "First name is mandatory field!") String firstName,
                               @NotBlank(message = "Last name is mandatory field") String lastName,
                               @Email(message = "Wrong email") String email,
                               @Size(min = 4, max = 8, message = "Size must be more than 4 and less than 8") String password) implements Serializable {

}
