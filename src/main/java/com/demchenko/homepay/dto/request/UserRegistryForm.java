package com.demchenko.homepay.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record UserRegistryForm(@Size(min = 2, max = 20) String firstName,
                               @Size(min = 2, max = 20) String lastName,
                               @Email String email,
                               @Size(min = 4, max = 16) String password) implements Serializable {

}
