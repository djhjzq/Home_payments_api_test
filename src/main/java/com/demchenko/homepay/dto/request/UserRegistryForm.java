package com.demchenko.homepay.dto.request;

import java.io.Serializable;

public record UserRegistryForm(String firstName, String lastName, String email,
                               String password) implements Serializable {

}
