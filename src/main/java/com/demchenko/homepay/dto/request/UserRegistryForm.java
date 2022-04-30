package com.demchenko.homepay.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistryForm {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
